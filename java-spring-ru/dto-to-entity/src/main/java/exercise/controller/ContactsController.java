package exercise.controller;

import exercise.dto.ContactCreateDTO;
import exercise.dto.ContactDTO;
import exercise.model.Contact;
import exercise.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(ContactCreateDTO contactData) {
        Contact contact = toEntity(contactData);//преобразование в Contact
        contactRepository.save(contact);
        ContactDTO contactDTO = toDTO(contact);
        return contactDTO;
    }

    private ContactDTO toDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setId(contact.getId());
        dto.setLastName(contact.getLastName());
        dto.setFirstName(contact.getFirstName());
        dto.setPhone(contact.getPhone());
        dto.setUpdatedAt(contact.getUpdatedAt());
        dto.setCreatedAt(contact.getCreatedAt());
        return dto;
    }

    private Contact toEntity(ContactCreateDTO contactData) {
        Contact contact = new Contact();
        contact.setFirstName(contactData.getFirstName());
        contact.setLastName(contactData.getLastName());
        contact.setPhone(contactData.getPhone());
        return contact;
    }
    // END
}
