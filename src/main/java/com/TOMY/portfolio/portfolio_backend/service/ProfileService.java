package com.TOMY.portfolio.portfolio_backend.service;

import com.TOMY.portfolio.portfolio_backend.dto.ProfileDTO;
import com.TOMY.portfolio.portfolio_backend.model.Profile;
import com.TOMY.portfolio.portfolio_backend.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO getProfile() {
        List<Profile> profiles = profileRepository.findAll();
        if (profiles.isEmpty()) {
            return null;
        }
        Profile profile = profiles.get(0);
        return convertToDTO(profile);
    }

    public ProfileDTO createOrUpdateProfile(ProfileDTO profileDTO) {
        List<Profile> existingProfiles = profileRepository.findAll();
        Profile profile;
        
        if (existingProfiles.isEmpty()) {
            profile = new Profile();
        } else {
            profile = existingProfiles.get(0);
        }

        profile.setFullName(profileDTO.getFullName());
        profile.setTitle(profileDTO.getTitle());
        profile.setLocation(profileDTO.getLocation());
        profile.setEmail(profileDTO.getEmail());
        profile.setPhone(profileDTO.getPhone());
        profile.setSummary(profileDTO.getSummary());

        Profile savedProfile = profileRepository.save(profile);
        return convertToDTO(savedProfile);
    }

    private ProfileDTO convertToDTO(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                profile.getFullName(),
                profile.getTitle(),
                profile.getLocation(),
                profile.getEmail(),
                profile.getPhone(),
                profile.getSummary()
        );
    }
}
