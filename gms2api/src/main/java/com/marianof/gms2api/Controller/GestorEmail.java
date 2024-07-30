package com.marianof.gms2api.Controller;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class GestorEmail {
    private static GestorEmail controller;
    private Properties properties;
    private Session session;
    private final String direccionEmail = "marianofdezrodero@gmail.com";
    private final String password = "ocqx lgvr tuww bjru";

    private GestorEmail() {

    }

    public static GestorEmail getSingleton() {
        if (controller == null)
            controller = new GestorEmail();
        return controller;
    }

    private void setPropertiesServidorSMTP() {
        properties = System.getProperties();
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.starttls.enable","true");
        session = Session.getInstance(properties, null);
    }

    private Transport conectarServidorSMPT() throws MessagingException {
        Transport t = session.getTransport("smtp");
        t.connect(properties.getProperty("mail.smtp.host"),direccionEmail,password);
        return t;
    }
    private Message crearNucleoMensaje(String emisor, String destinatario, String asunto) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emisor));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        message.setSubject(asunto);
        return message;
    }
    private Message crearMensajeTexto(String emisor, String destinatario, String asunto, String textoMensaje) throws MessagingException {
        Message message = crearNucleoMensaje(emisor,destinatario,asunto);
        message.setContent(textoMensaje, "text/html; charset=utf-8");
        return message;
    }
    public void enviarMensajeTexto(String destinatario, String asunto, String textoMensaje) {
        setPropertiesServidorSMTP();
        try {
            Message message = crearMensajeTexto(this.direccionEmail, destinatario, asunto, textoMensaje);
            Transport transport = conectarServidorSMPT();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}