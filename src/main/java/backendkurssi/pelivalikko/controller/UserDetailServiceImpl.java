package backendkurssi.pelivalikko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import backendkurssi.pelivalikko.domain.Pelaaja;
import backendkurssi.pelivalikko.domain.PelaajaRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	private final PelaajaRepository repository;

	@Autowired
	public UserDetailServiceImpl(PelaajaRepository userRepository) {
		this.repository = userRepository;
	}

    @Override
    public UserDetails loadUserByUsername(String pelaajanimi) throws UsernameNotFoundException
    {   
    	Pelaaja curruser = repository.findByPelaajanimi(pelaajanimi);
        UserDetails user = new org.springframework.security.core.userdetails.User(pelaajanimi, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }   
}

