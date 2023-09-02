package com.sistemacar.parkapi;

import com.sistemacar.parkapi.DTO.UsuarioCreateDTO;
import com.sistemacar.parkapi.DTO.UsuarioResponseDTO;
import com.sistemacar.parkapi.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UsuarioIT {

    @Autowired(required = true)
    WebTestClient testClient;

    @Test
    public void createUsuario_ConUsernamePasswordValidos_RetornarUsuarioCriadocomStatus201(){
       UsuarioResponseDTO responseBody = testClient
                .post()
                .uri("/usuarios/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDTO("tody@gmail.com", "123456"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UsuarioResponseDTO.class)
                .returnResult().getResponseBody();

       org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
       org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
       org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isEqualTo("tody@gmail.com");
       org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("CLIENT");
    }
    @Test
    public void createUsuario_ConUsernameInvalidos_RetornarErrorMenssageStatus422(){
        ErrorMessage responseBody = testClient
                .post()
                .uri("/usuarios/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDTO("", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        responseBody = testClient
                .post()
                .uri("/usuarios/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDTO("tody@", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        responseBody = testClient
                .post()
                .uri("/usuarios/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDTO("tody@gmail", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }

    @Test
    public void createUsuario_ConUsernameRepetido_RetornarErrorMessageComStatus409(){
        ErrorMessage responseBody = testClient
                .post()
                .uri("/usuarios/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDTO("angel@gmail.com", "123456"))
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(409);


    }

}
