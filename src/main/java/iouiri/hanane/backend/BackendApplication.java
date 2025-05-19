package iouiri.hanane.backend;

import iouiri.hanane.backend.entities.*;
import iouiri.hanane.backend.enums.StatutCredit;
import iouiri.hanane.backend.enums.TypeRemboursement;
import iouiri.hanane.backend.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            ClientRepository clientRepository,
            CreditRepository creditRepository,
            RemboursementRepository remboursementRepository) {
        return args -> {

            // Création des clients
            Stream.of("Youssef", "Fatima", "Imane").forEach(name -> {
                Client client = new Client();
                client.setNom(name);
                client.setEmail(name.toLowerCase() + "@gmail.com");
                clientRepository.save(client);
            });

            // Ajout des crédits à chaque client
            clientRepository.findAll().forEach(client -> {

                // Crédit Personnel
                CreditPersonnel cp = new CreditPersonnel();
                cp.setDateDemande(new Date());
                cp.setStatut(StatutCredit.EN_COURS);
                cp.setMontant(100000);
                cp.setDuree(36);
                cp.setTauxInteret(4.5f);
                cp.setMotif("Achat voiture");
                cp.setClient(client);
                creditRepository.save(cp);

                // Crédit Immobilier
                CreditImmobilier ci = new CreditImmobilier();
                ci.setDateDemande(new Date());
                ci.setStatut(StatutCredit.ACCEPTE);
                ci.setDateAcceptation(new Date());
                ci.setMontant(500000);
                ci.setDuree(240);
                ci.setTauxInteret(3.5f);
                ci.setTypeBien("Maison");
                ci.setClient(client);
                creditRepository.save(ci);

                // Crédit Professionnel
                CreditProfessionnel crp = new CreditProfessionnel();
                crp.setDateDemande(new Date());
                crp.setStatut(StatutCredit.REJETE);
                crp.setMontant(200000);
                crp.setDuree(60);
                crp.setTauxInteret(5.2f);
                crp.setMotif("Matériel informatique");
                crp.setRaisonSociale("SARL TechDev");
                crp.setClient(client);
                creditRepository.save(crp);
            });

            // Remboursements
            creditRepository.findAll().forEach(credit -> {
                for (int i = 0; i < 3; i++) {
                    Remboursement r = new Remboursement();
                    r.setDate(new Date());
                    r.setMontant(credit.getMontant() / 10);
                    r.setType(i == 2 ? TypeRemboursement.REMBOURSEMENT_ANTICIPE : TypeRemboursement.MENSUALITE);
                    r.setCredit(credit);
                    remboursementRepository.save(r);
                }
            });
        };
    }
}
