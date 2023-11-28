package backendkurssi.pelivalikko.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import backendkurssi.pelivalikko.domain.Ammatti;
import backendkurssi.pelivalikko.domain.AmmattiRepository;
import backendkurssi.pelivalikko.domain.Hahmo;
import backendkurssi.pelivalikko.domain.HahmoRepository;
import backendkurssi.pelivalikko.domain.PelaajaRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;

@Controller
public class HahmoController {
	@Autowired
	private HahmoRepository repository;
	@Autowired
	private AmmattiRepository arepository;
	@Autowired
	private PelaajaRepository prepository;

	private Random random = new Random();

	private boolean hahmoMuuttunut(Hahmo vanhaHahmo, Hahmo uusiHahmo) {
		return !Objects.equals(vanhaHahmo.getNimi(), uusiHahmo.getNimi())
				|| !Objects.equals(vanhaHahmo.getIka(), uusiHahmo.getIka())
				|| !Objects.equals(vanhaHahmo.getKokemuspisteet(), uusiHahmo.getKokemuspisteet())
				|| !Objects.equals(vanhaHahmo.getAmmatti(), uusiHahmo.getAmmatti());
	}

	private String generoiSatunnainenNimi() {
		String[] nimet = { "Guillermo", "Hildegard", "Mustaparta", "Ansgarius", "Saga" };
		String satunnainenNimi = nimet[random.nextInt(nimet.length)];
		return satunnainenNimi;
	}

	@RequestMapping(value = { "/", "/varustus" })
	public String hahmoLista(Model model) {
		model.addAttribute("hahmot", repository.findAll());
		return "varustus";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/varvaa")
	public String lisaaHahmo(Model model) {
		model.addAttribute("hahmo", new Hahmo());
		model.addAttribute("ammatit", arepository.findAll());
		return "varvaa";
	}

	@RequestMapping(value = "/tallenna", method = RequestMethod.POST)
	public String tallenna(Hahmo hahmo, RedirectAttributes redirectAttributes) {

		hahmo.setNimi(generoiSatunnainenNimi());
		hahmo.setKokemuspisteet(random.nextInt(996) + 111);
		hahmo.setIka(random.nextInt(60) + 16);

		repository.save(hahmo);
		redirectAttributes.addFlashAttribute("vahvistus",
				hahmo.getAmmatti().getNimike() + " " + hahmo.getNimi() + " " + "liittynyt ryhmään!");
		return "redirect:varvaa";
	}

	@RequestMapping(value = "/tallenna/{id}", method = RequestMethod.POST)
	public String tallennaMuutos(@PathVariable Long id, @Valid Hahmo uusiHahmo, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		String muuttuneenNimi = "";
		Hahmo vanhaHahmo = repository.findById(id).get();

		if (!hahmoMuuttunut(vanhaHahmo, uusiHahmo)) {
			return "redirect:../muuta";
		}
		if (id.equals(1L)) {
			muuttuneenNimi = prepository.findById(id).get().getPelaajanimi();
		} else {
			muuttuneenNimi = vanhaHahmo.getNimi();
		}
		repository.save(uusiHahmo);
		redirectAttributes.addFlashAttribute("vahvistus", "Seikkailijan " + muuttuneenNimi + " identiteetti muuttui ");
		return "redirect:../muuta";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/poista/{id}", method = RequestMethod.GET)
	public String poistaHahmo(@PathVariable("id") Long hahmoId, Model model, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("vahvistus", "Hyvästi " + repository.findById(hahmoId).get().getNimi() + "!");
		repository.deleteById(hahmoId);
		return "redirect:../varustus";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/muuta", method = RequestMethod.GET)
	public String muutaHahmo(Hahmo uusiHahmo, Model model) {		
		model.addAttribute("uusihahmo", uusiHahmo);
		model.addAttribute("hahmot", repository.findAll());
		model.addAttribute("ammatit", arepository.findAll());
		return "identiteetti";
	}

	@RequestMapping(value = { "/restsivu" })
	public String tietosivu() {
		return "restsivu";
	}

	// Rest

	@RequestMapping(value = "/hahmot", method = RequestMethod.GET)
	public @ResponseBody List<Hahmo> hahmoListaRest() {
		return (List<Hahmo>) repository.findAll();
	}

	@RequestMapping(value = "/hahmo/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Hahmo> loydaHahmoRest(@PathVariable("id") Long hahmoId) {
		return repository.findById(hahmoId);
	}

	@RequestMapping(value = "/ammatit", method = RequestMethod.GET)
	public @ResponseBody List<Ammatti> ammattiListaRest() {
		return (List<Ammatti>) arepository.findAll();
	}

}
