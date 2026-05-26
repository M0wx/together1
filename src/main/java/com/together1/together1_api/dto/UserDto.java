package com.together1.together1_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO exposé par l'API. Il ne contient jamais le champ {@code password}
 * afin de ne jamais renvoyer de hash en clair au client.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
}
