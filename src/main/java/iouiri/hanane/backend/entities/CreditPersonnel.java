package iouiri.hanane.backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PERSONNEL")
@Data @NoArgsConstructor @AllArgsConstructor
public class CreditPersonnel extends Credit {
    private String motif;
}
