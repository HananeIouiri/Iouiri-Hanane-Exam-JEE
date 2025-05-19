package iouiri.hanane.backend.dtos;

import iouiri.hanane.backend.enums.TypeRemboursement;
import lombok.Data;

import java.util.Date;

@Data
public class RemboursementDTO {
    private Long id;
    private Date date;
    private double montant;
    private TypeRemboursement type;
    private CreditDTO credit;
}
