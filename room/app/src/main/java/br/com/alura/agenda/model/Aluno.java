package br.com.alura.agenda.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity(tableName = "aluno")
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nome;
    private String email;

    private LocalDateTime momentoCadastro = LocalDateTime.now();

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    @Ignore
    private List<Telefone> telefones;

    public String getEmail() {
        return email;
    }

    public LocalDateTime getMomentoCadastro() {
        return momentoCadastro;
    }

    public void setMomentoCadastro(LocalDateTime momentoCadastro) {
        this.momentoCadastro = momentoCadastro;
    }

    public boolean temIdValido() {
        return id > 0;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
