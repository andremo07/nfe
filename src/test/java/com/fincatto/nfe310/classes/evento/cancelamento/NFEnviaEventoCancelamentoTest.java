package com.fincatto.nfe310.classes.evento.cancelamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.fincatto.nfe310.FabricaDeObjetosFake;

public class NFEnviaEventoCancelamentoTest {

    @Test
    public void deveObterEventosComoFoiSetado() {
        final NFEnviaEventoCancelamento eventoCancelamento = new NFEnviaEventoCancelamento();
        final ArrayList<NFEventoCancelamento> eventosCancelamento = new ArrayList<NFEventoCancelamento>();
        eventoCancelamento.setEvento(eventosCancelamento);
        Assert.assertEquals(eventosCancelamento, eventoCancelamento.getEvento());
    }

    @Test
    public void deveObterIdLoteComoFoiSetado() {
        final NFEnviaEventoCancelamento eventoCancelamento = new NFEnviaEventoCancelamento();
        eventoCancelamento.setIdLote("1");
        Assert.assertEquals("1", eventoCancelamento.getIdLote());
    }

    @Test
    public void deveObterVersaoStringComoFoiSetado() {
        final NFEnviaEventoCancelamento eventoCancelamento = new NFEnviaEventoCancelamento();
        final BigDecimal versao = new BigDecimal("3.10");
        eventoCancelamento.setVersao(versao);
        Assert.assertEquals(versao.toString(), eventoCancelamento.getVersao());
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirEventosNulo() {
        final NFEnviaEventoCancelamento eventoCancelamento = new NFEnviaEventoCancelamento();
        eventoCancelamento.setIdLote("1");
        eventoCancelamento.setVersao(new BigDecimal("3.10"));
        eventoCancelamento.toString();
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirLoteNulo() {
        final NFEnviaEventoCancelamento eventoCancelamento = new NFEnviaEventoCancelamento();
        eventoCancelamento.setEvento(new ArrayList<NFEventoCancelamento>());
        eventoCancelamento.setVersao(new BigDecimal("3.10"));
        eventoCancelamento.toString();
    }

    @Test(expected = IllegalStateException.class)
    public void naoDevePermitirVersaoNulo() {
        final NFEnviaEventoCancelamento eventoCancelamento = new NFEnviaEventoCancelamento();
        eventoCancelamento.setEvento(new ArrayList<NFEventoCancelamento>());
        eventoCancelamento.setIdLote("1");
        eventoCancelamento.toString();
    }

    @Test
    public void deveGerarXMLDeAcordoComOPadraoEstabelecido() {
        final NFEnviaEventoCancelamento eventoCancelamento = new NFEnviaEventoCancelamento();
        eventoCancelamento.setEvento(Arrays.asList(FabricaDeObjetosFake.getNFEventoCancelamento()));
        eventoCancelamento.setVersao(new BigDecimal("3.10"));
        eventoCancelamento.setIdLote("1");

        final String xmlEsperado = "<envEvento versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\"><idLote>1</idLote><evento versao=\"3.10\"><infEvento Id=\"hluU2zKt4QK5bEktOiGfpZw64535p2A4Z5m5egLQbMpjnCH48c1aw6\"><cOrgao>42</cOrgao><tpAmb>2</tpAmb><CNPJ>12345678901234</CNPJ><chNFe>81568004734874930428983724940883089298523837</chNFe><dhEvento>2014-01-01T10:10:10-02:00</dhEvento><tpEvento>123456</tpEvento><nSeqEvento>2</nSeqEvento><verEvento>2.49</verEvento><detEvento versao=\"3.10\"><descEvento>Cancelamento</descEvento><nProt>123456789012345</nProt><xJust>Justificativa qualquer coisa</xJust></detEvento></infEvento></evento></envEvento>";
        Assert.assertEquals(xmlEsperado, eventoCancelamento.toString());
    }
}