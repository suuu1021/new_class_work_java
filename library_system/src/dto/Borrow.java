package dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Borrow {

    private int id;
    private int studentId;
    private int bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

}
