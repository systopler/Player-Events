package ru.moa.player.events;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.moa.player.events.enums.ProtocolEnum;
import ru.moa.player.events.service.SMTPService;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FlagTerm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventApplicationTest {
    @Autowired
    private SMTPService smtpService;

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

        deleteByFrom(messages, "Nataly.Kapishnikova@svyazintek.ru");
        deleteByFrom(messages, "nkapishnikova@at-consulting.ru");
        deleteByFrom(messages, "info@site.hh.ru");
        deleteByFrom(messages, "evorobieva@oebs.sinvest.ru");
        deleteByFrom(messages, "Tatiana.Naumova@svyazintek.ru");
        deleteByFrom(messages, "omityushin@oebs.sinvest.ru");

        

        //inbox.setFlags(n, n, new Flags(Flags.Flag.DELETED), true);
        inbox.close(true);
        store.close();
    }
}
