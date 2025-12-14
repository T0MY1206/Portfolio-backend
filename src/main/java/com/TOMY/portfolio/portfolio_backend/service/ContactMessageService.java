package com.TOMY.portfolio.portfolio_backend.service;

import com.TOMY.portfolio.portfolio_backend.dto.ContactMessageDTO;
import com.TOMY.portfolio.portfolio_backend.dto.ContactMessageRequestDTO;
import com.TOMY.portfolio.portfolio_backend.model.ContactMessage;
import com.TOMY.portfolio.portfolio_backend.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    public List<ContactMessageDTO> getAllMessages() {
        return contactMessageRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ContactMessageDTO> getUnreadMessages() {
        return contactMessageRepository.findByIsReadFalseOrderByCreatedAtDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ContactMessageDTO getMessageById(Long id) {
        ContactMessage message = contactMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado con id: " + id));
        return convertToDTO(message);
    }

    public ContactMessageDTO createMessage(ContactMessageRequestDTO requestDTO) {
        ContactMessage message = new ContactMessage();
        message.setName(requestDTO.getName());
        message.setEmail(requestDTO.getEmail());
        message.setMessage(requestDTO.getMessage());

        ContactMessage savedMessage = contactMessageRepository.save(message);
        return convertToDTO(savedMessage);
    }

    public ContactMessageDTO markAsRead(Long id) {
        ContactMessage message = contactMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado con id: " + id));
        
        message.setIsRead(true);
        ContactMessage updatedMessage = contactMessageRepository.save(message);
        return convertToDTO(updatedMessage);
    }

    public void deleteMessage(Long id) {
        if (!contactMessageRepository.existsById(id)) {
            throw new RuntimeException("Mensaje no encontrado con id: " + id);
        }
        contactMessageRepository.deleteById(id);
    }

    public Long getUnreadCount() {
        return contactMessageRepository.countByIsReadFalse();
    }

    private ContactMessageDTO convertToDTO(ContactMessage message) {
        return new ContactMessageDTO(
                message.getId(),
                message.getName(),
                message.getEmail(),
                message.getMessage(),
                message.getCreatedAt(),
                message.getIsRead()
        );
    }
}
