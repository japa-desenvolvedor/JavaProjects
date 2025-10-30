package edu.senai.pietro.simuladorrpg.model;

/**
 * Enum que representa os diversos itens de equipamento do jogo,
 * cada um com um nome, um bônus de combate e o tipo de equipamento.
 */
public enum Equipamento {
    // Cabeçais
    ELMO_DA_IGNORANCIA_GLORIOSA("Elmo da Ignorância Gloriosa", 1, TipoEquipamento.CABECAL),
    CHAPEU_PONTUDO_DE_TRES_PONTAS("Chapéu Pontudo de Três Pontas", 2, TipoEquipamento.CABECAL),
    BANDANA_DA_FORCA_BRUTA("Bandana da Força Bruta (e Pouca Inteligência)", 3, TipoEquipamento.CABECAL),

    // Armaduras
    ARMADURA_DE_COURO_FALSO("Armadura de Couro Falso", 1, TipoEquipamento.ARMADURA),
    CAMISETA_DA_BANDA_DE_BARDO("Camiseta da Banda de Bardo", 1, TipoEquipamento.ARMADURA),
    COTA_DE_MALHA_ELETRIFICADA("Cota de Malha Eletrificada (Dá choque em você)", 3, TipoEquipamento.ARMADURA),
    ARMADURA_DE_PLOT_CONVENIENTE("Armadura de Plot Conveniente", 4, TipoEquipamento.ARMADURA),
    
    // Calçados
    BOTAS_DO_CHUTE_NA_PORTA("Botas do Chute na Porta", 1, TipoEquipamento.CALCADO),
    SANDALIAS_DA_FUGA_APAVORADA("Sandálias da Fuga Apavorada", 2, TipoEquipamento.CALCADO),
    TENIS_DE_CORRIDA_EM_LINHA_RETA("Tênis de Corrida (mas só em linha reta)", 2, TipoEquipamento.CALCADO),

    // Itens de Mão (Armas e Escudos)
    ADAGA_DE_MANTEIGA("Adaga de Manteiga", 1, TipoEquipamento.MAO),
    PORRETE_COM_UM_PREGO("Porrete com um Prego (só um)", 1, TipoEquipamento.MAO),
    ESCUDO_DE_PAPELAO_REFORCADO("Escudo de Papelão Reforçado com Fita Adesiva", 2, TipoEquipamento.MAO),
    ESPADA_DE_DUAS_MAOS_E_MEIA("Espada de Duas Mãos e Meia", 3, TipoEquipamento.MAO),
    CAJADO_DO_ESTAGIARIO_MAGICO("Cajado do Estagiário Mágico", 3, TipoEquipamento.MAO),
    LUVAS_DE_BOXE_RASGADAS("Luvas de Boxe Rasgadas", 3, TipoEquipamento.MAO),
    ARCO_QUE_SO_ATIRA_PARA_TRAS("Arco que Só Atira para Trás", 4, TipoEquipamento.MAO),
    MOTOSSERRA_DA_EVISCERACAO_SANGRENTA("Motosserra da Evisceração Sangrenta", 5, TipoEquipamento.MAO),

    // Itens Gerais
    POCAO_DA_CORAGEM_INSTANTANEA("Poção da Coragem Instantânea (e Arrependimento)", 2, TipoEquipamento.ITEM_GERAL),
    ANEL_DO_PODER_MEDIANO("Anel do Poder Mediano", 2, TipoEquipamento.ITEM_GERAL),
    JOELHEIRAS_DA_SEDUCAO("Joelheiras da Sedução", 2, TipoEquipamento.ITEM_GERAL),
    MANUAL_DE_INSTRUCOES_DO_JOGO("Manual de Instruções do Jogo (Lido pela metade)", 1, TipoEquipamento.ITEM_GERAL),
    AMULETO_DA_SORTE_DE_OUTRA_PESSOA("Amuleto da Sorte (de Outra Pessoa)", 1, TipoEquipamento.ITEM_GERAL),
    LANTERNA_QUE_ATRAI_INSETOS("Lanterna que Atrai Insetos", 0, TipoEquipamento.ITEM_GERAL);

    private final String nome;
    private final int bonus;
    private final TipoEquipamento tipo;
    
    public enum TipoEquipamento {
    CABECAL,
    ARMADURA,
    CALCADO,
    MAO,
    ITEM_GERAL
}

    Equipamento(String nome, int bonus, TipoEquipamento tipo) {
        this.nome = nome;
        this.bonus = bonus;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public int getBonus() {
        return bonus;
    }

    public TipoEquipamento getTipo() {
        return tipo;
    }
    
    public String toString(){
        return nome + " +" + bonus;
    }
}