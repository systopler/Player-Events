package ru.moa.player.events;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.moa.player.events.db.entity.OrganizationTypeEntity;
import ru.moa.player.events.db.entity.PersonEntity;
import ru.moa.player.events.db.entity.contact.ContactTypesEntity;
import ru.moa.player.events.db.entity.contact.PersonContactEntity;
import ru.moa.player.events.db.repository.*;
import ru.moa.player.events.service.SMTPService;
import ru.moa.player.events.db.entity.OrganizationEntity;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FlagTerm;
import java.text.ParseException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EventApplicationTest {
    @Autowired
    private SMTPService smtpService;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactTypesRepository contactTypesRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private OrganizationsRepository organizationsRepository;

    @Autowired
    private OrganizationTypeRepository organizationTypeRepository;


    private void deleteByFrom(Message[] messages, String address) throws AddressException {
        InternetAddress from = new InternetAddress(address);
        for (Message message : messages){
            try {
                if (message.getFrom() != null && message.getFrom().length > 0){
                    for (Address addr : message.getFrom()){
                        if (addr.equals(from)){
                            System.out.println(String.format("Message = %s date = %s from = %s delete", message.getSubject(), message.getSentDate().toString(), message.getFrom()[0]));
                            message.setFlag(Flags.Flag.DELETED, true);
                        }
                    }
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void removeOldMessage() throws MessagingException, ParseException {
        Session session = smtpService.getSession();

        Store store = session.getStore("pop3");

        store.connect();
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);
        Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

        /*
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        String dateInString = "01.01.2010";
        Date date = formatter.parse(dateInString);


        for (Message message : messages){
            if (message.getSentDate() != null) {
                try{
                    if (message.getSentDate().before(date)){
                        System.out.println(String.format("Message = %s date = %s delete", message.getSubject(), message.getSentDate().toString()));
                        message.setFlag(Flags.Flag.DELETED, true);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
         */

        /*
        deleteByFrom(messages, "Nataly.Kapishnikova@svyazintek.ru");
        deleteByFrom(messages, "nkapishnikova@at-consulting.ru");
        deleteByFrom(messages, "info@site.hh.ru");
        deleteByFrom(messages, "evorobieva@oebs.sinvest.ru");
        deleteByFrom(messages, "Tatiana.Naumova@svyazintek.ru");
        deleteByFrom(messages, "omityushin@oebs.sinvest.ru");

         */



        //inbox.setFlags(n, n, new Flags(Flags.Flag.DELETED), true);
        inbox.close(true);
        store.close();
    }

    @Test
    @Transactional
    public void addPerson(){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setLastName("Митюшин");
        personEntity.setFirstName("Олег");
        personEntity.setObjectVersionNumber(1L);

        PersonContactEntity contact = new PersonContactEntity();
        contact.setObjectType("PERSON");
        contact.setObjectVersionNumber(1L);
        contact.setContactTypesEntity(contactTypesRepository.getOne(1L));
        contact.setValue("23232323");
        personEntity.addContact(contact);

        peopleRepository.save(personEntity);
    }

    @Test
    @Transactional
    public void addContact(){
        ContactTypesEntity contactTypes = contactTypesRepository.getOne(1L);
        PersonContactEntity contact = new PersonContactEntity();
        //contact.setObjectType("PERSON");
        contact.setPerson(new PersonEntity());
        //contact.setVersion(1L);
        contact.setContactTypesEntity(contactTypes);

        System.out.println(String.format("contactTypes = %s", contact.toString()));

        //contactRepository.save(contact);
    }

    @Test
    @Transactional
    public void addOrganization(){
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setObjectVersionNumber(1L);
        organizationEntity.setPackName("pack");
        organizationEntity.setFullName("full");

        //organizationEntity.setOrganizationType(organizationTypeRepository.findById(1L).orElse(null));



        organizationsRepository.saveAndFlush(organizationEntity);

        //contactRepository.save(contact);
    }

    @Test
    @Transactional
    public void updateOrganization(){
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setObjectVersionNumber(1L);
        organizationEntity.setPackName("pack");
        organizationEntity.setFullName("full");

        //OrganizationTypeEntity organizationTypeEntity = organizationTypeRepository.findById(1L).orElse(null);
        //organizationEntity.setOrganizationType(organizationTypeEntity);

        organizationEntity

        organizationEntity = organizationsRepository.saveAndFlush(organizationEntity);
    }

    @Test
    @Transactional
    public void findOrganizationType(){
        OrganizationTypeEntity organizationTypeEntity = organizationTypeRepository.findById(1L).orElse(null);
        System.out.println(String.format("organizationTypeEntity = %s", organizationTypeEntity.toString()));
    }
}
