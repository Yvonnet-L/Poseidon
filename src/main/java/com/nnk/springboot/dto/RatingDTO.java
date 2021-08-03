package com.nnk.springboot.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 *  RatingDTO is the object of the view which makes the link with the Rating model
 * @See Rating
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {

    private Integer id;

    @NotBlank(message = "Account is mandatory")
    private String moodysRating;

    @NotBlank(message = "Account is mandatory")
    private String sandRating;

    @NotBlank(message = "Account is mandatory")
    private String fitchRating;

    @Min(value = 0 , message ="must be positif")
    private Integer orderNumber;

    //----------Constructor-----------------------------------------------------------------------------------

    public RatingDTO(String moodysRating, String sandRating, String fitchRating, Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandRating = sandRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }
}
