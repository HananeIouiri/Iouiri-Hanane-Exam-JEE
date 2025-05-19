package iouiri.hanane.backend.dtos;

import iouiri.hanane.backend.enums.StatutCredit;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreditDTO {
    private Long id;
    private Date dateDemande;
    private StatutCredit statut;
    private Date dateAcceptation;
    private double montant;
    private int duree;
    private float tauxInteret;
    private ClientDTO client;
    private List<RemboursementDTO> remboursements;
}
