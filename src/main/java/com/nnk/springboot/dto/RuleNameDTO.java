package com.nnk.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 *  RuleNameDTO is the object of the view which makes the link with the RuleName model
 * @See Rating
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuleNameDTO {

    private Integer id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "description is mandatory")
    private String description;

    private String json;

    private String template;

    private String sqlStr;

    private String sqlPart;

    //------------------ Constructor -------------------------------------------------------

    public RuleNameDTO(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }
}
