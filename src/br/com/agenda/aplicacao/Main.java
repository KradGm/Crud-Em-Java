package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {
    public static void main(String[] args) {
        ContatoDAO contatoDAO = new ContatoDAO();

        Contato contato = new Contato();
        contato.setNome("Novo Oliveira");
        contato.setIdade(20);
        contato.setDataCadastro(new Date());

        contatoDAO.save(contato);

        Contato contatoAtualizado = new Contato();
        contatoAtualizado.setNome("Zeno kkk");
        contatoAtualizado.setIdade(16);
        contatoAtualizado.setDataCadastro(new Date());
        contatoAtualizado.setId(6);

        contatoDAO.update(contatoAtualizado);

        contatoDAO.deleteByID(11);

        for (Contato C : contatoDAO.getContatos()) {
            System.out.println("Contato: " + C.getNome()+" - Idade do contato: "+C.getIdade()+" Id - "+C.getId());
        }


    }
}
