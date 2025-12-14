package com.TOMY.portfolio.portfolio_backend.service;

import com.TOMY.portfolio.portfolio_backend.dto.LanguageDTO;
import com.TOMY.portfolio.portfolio_backend.model.Language;
import com.TOMY.portfolio.portfolio_backend.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public List<LanguageDTO> getAllLanguages() {
        return languageRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LanguageDTO getLanguageById(Long id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Idioma no encontrado con id: " + id));
        return convertToDTO(language);
    }

    public LanguageDTO createLanguage(LanguageDTO languageDTO) {
        if (languageRepository.existsByName(languageDTO.getName())) {
            throw new RuntimeException("El idioma ya existe");
        }

        Language language = new Language();
        language.setName(languageDTO.getName());
        language.setLevel(languageDTO.getLevel());

        Language savedLanguage = languageRepository.save(language);
        return convertToDTO(savedLanguage);
    }

    public LanguageDTO updateLanguage(Long id, LanguageDTO languageDTO) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Idioma no encontrado con id: " + id));

        if (!language.getName().equals(languageDTO.getName()) && 
            languageRepository.existsByName(languageDTO.getName())) {
            throw new RuntimeException("El idioma ya existe");
        }

        language.setName(languageDTO.getName());
        language.setLevel(languageDTO.getLevel());

        Language updatedLanguage = languageRepository.save(language);
        return convertToDTO(updatedLanguage);
    }

    public void deleteLanguage(Long id) {
        if (!languageRepository.existsById(id)) {
            throw new RuntimeException("Idioma no encontrado con id: " + id);
        }
        languageRepository.deleteById(id);
    }

    private LanguageDTO convertToDTO(Language language) {
        return new LanguageDTO(
                language.getId(),
                language.getName(),
                language.getLevel()
        );
    }
}
