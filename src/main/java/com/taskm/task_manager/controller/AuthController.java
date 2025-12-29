package com.taskm.task_manager.controller;


import com.taskm.task_manager.security.CustomUserDetails;
import com.taskm.task_manager.security.JwtUtil;
import com.taskm.task_manager.service.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


//@RestController
//@RequestMapping("/api")
//public class AuthController {
//
//    private final AuthenticationManager authManager;
//
//    public AuthController(AuthenticationManager authManager) {
//        this.authManager = authManager;
//    }

//    @PostMapping("/login")
//    public ResponseEntity<Problem> login(@RequestParam LoginRequest username, @RequestParam LoginRequest password) {
//        System.out.println("➡ Ricevuto login: " + username.getUsername() + " / " + password.getPassword());
//        try {
//            Authentication auth = authManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username.getUsername(), password.getPassword())
//            );
//            System.out.println("✔ Login riuscito");
//            return ResponseEntity.ok(new Problem("200", "login ok", HttpStatus.OK.value(), "login è andato bene ", "null"));
//        } catch (BadCredentialsException e) {
//            System.out.println("❌ Credenziali errate");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Problem("403", "login NN AUTH", HttpStatus.UNAUTHORIZED.value(), "login NON AUTH ", "null"));
//        } catch (Exception e) {
//            System.out.println("forse siamo qui" + e.getMessage());
//             return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Problem("500", "login NO", HttpStatus.INTERNAL_SERVER_ERROR.value(), "login NO", "null"));
//        }
//    }


//    @PostMapping("/login")
//    public ResponseEntity<Problem> login(@RequestBody LoginRequest request) {
//        System.out.println("➡ Ricevuto login: " + request.getUsername() + " / " + request.getPassword());
//        try {
//            Authentication auth = authManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//            );
//            SecurityContextHolder.getContext().setAuthentication(auth);
//            System.out.println("✔ Login riuscito");
//            return ResponseEntity.ok(new Problem("200", "login ok", HttpStatus.OK.value(), "login è andato bene ", "null"));
//        } catch (BadCredentialsException e) {
//            System.out.println("❌ Credenziali errate");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(new Problem("403", "login NN AUTH", HttpStatus.UNAUTHORIZED.value(), "login NON AUTH ", "null"));
//        } catch (Exception e) {
//            System.out.println("forse siamo qui" + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new Problem("500", "login NO", HttpStatus.INTERNAL_SERVER_ERROR.value(), "login NO", "null"));
//        }
//    }
@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(AuthenticationManager am, JwtUtil jwtUtil, CustomUserDetailsService uds) {
        this.authManager = am;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = uds;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            final String token = jwtUtil.generateToken(userDetails.getUsername());


            // puoi anche ritornare ruolo se vuoi
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "username", userDetails.getUsername(),
                    "ruolo", userDetails.getRuolo().toString(),
                    "anagraficaId", userDetails.getAnagraficaId()

            ));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenziali errate"));
        }
    }
}




