package com.TOMY.portfolio.portfolio_backend.controller;

import com.TOMY.portfolio.portfolio_backend.dto.ContactMessageDTO;
import com.TOMY.portfolio.portfolio_backend.dto.ContactMessageRequestDTO;
import com.TOMY.portfolio.portfolio_backend.service.ContactMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@Tag(name = "Contact", description = "API para gestionar mensajes de contacto")
public class ContactMessageController {

    @Autowired
    private ContactMessageService contactMessageService;

    @PostMapping
    @Operation(summary = "Enviar mensaje de contacto", description = "Crea un nuevo mensaje de contacto desde el formulario")
    public ResponseEntity<ContactMessageDTO> sendMessage(@Valid @RequestBody ContactMessageRequestDTO requestDTO) {
        ContactMessageDTO createdMessage = contactMessageService.createMessage(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los mensajes", description = "Retorna una lista de todos los mensajes de contacto (admin)")
    public ResponseEntity<List<ContactMessageDTO>> getAllMessages() {
        List<ContactMessageDTO> messages = contactMessageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/unread")
    @Operation(summary = "Obtener mensajes no leídos", description = "Retorna una lista de mensajes no leídos (admin)")
    public ResponseEntity<List<ContactMessageDTO>> getUnreadMessages() {
        List<ContactMessageDTO> messages = contactMessageService.getUnreadMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/unread/count")
    @Operation(summary = "Contar mensajes no leídos", description = "Retorna el número de mensajes no leídos (admin)")
    public ResponseEntity<Long> getUnreadCount() {
        Long count = contactMessageService.getUnreadCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener mensaje por ID", description = "Retorna un mensaje específico por su ID (admin)")
    public ResponseEntity<ContactMessageDTO> getMessageById(@PathVariable Long id) {
        ContactMessageDTO message = contactMessageService.getMessageById(id);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/{id}/read")
    @Operation(summary = "Marcar mensaje como leído", description = "Marca un mensaje como leído (admin)")
    public ResponseEntity<ContactMessageDTO> markAsRead(@PathVariable Long id) {
        ContactMessageDTO updatedMessage = contactMessageService.markAsRead(id);
        return ResponseEntity.ok(updatedMessage);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar mensaje", description = "Elimina un mensaje por su ID (admin)")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        contactMessageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
