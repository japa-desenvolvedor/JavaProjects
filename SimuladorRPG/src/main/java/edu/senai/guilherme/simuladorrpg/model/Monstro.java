package edu.senai.guilherme.simuladorrpg.model;

/**
 * Enum que representa os monstros encontrados no jogo,
 * cada um com seu nome característico e nível de combate.
 */
public enum Monstro {
    GOBLIN_ESTAGIARIO("Goblin Estagiário", 1),
    PATO_DO_APOCALIPSE("Pato do Apocalipse", 1),
    PLANTA_DE_VASO_CARNIVORA("Planta de Vaso Carnívora", 2),
    MINHOCA_NARIGUDA("Minhoca Nariguda", 2),
    ESQUELETO_ADVOGADO("Esqueleto Advogado", 3),
    HARPIA_CANTORA_DE_OPERA("Harpia Cantora de Ópera", 3),
    ORC_DE_TELEMARKETING("Orc de Telemarketing", 4),
    GAZELA_MUTANTE_VINGATIVA("Gazela Mutante Vingativa", 4),
    FANTASMA_TIMIDO("Fantasma Tímido", 5),
    SLIME_PEGAJOSO("Slime Pegajoso", 5),
    ZUMBI_GOTEJANTE("Zumbi Gotejante", 6),
    POLTRONA_MIMETICA("Poltrona Mimética", 6),
    HIPOGRIFO_MIOPE("Hipogrifo Míope", 7),
    BUROCRATA_FLUTUANTE("Burocrata Flutuante", 7),
    VAMPIRO_DE_MEIA_TIGELA("Vampiro de Meia-Tigela", 8),
    TROLL_DA_INTERNET("Troll da Internet", 8),
    DEMONIO_DA_PROCASTINACAO("Demônio da Procrastinação", 9),
    GOLEM_DE_QUEIJO("Golem de Queijo", 9),
    HIDRA_DE_DUAS_CABEÇAS_E_MEIA("Hidra de Duas Cabeças e Meia", 10),
    CRITICO_DE_CINEMA_AMADOR("Crítico de Cinema Amador", 10),
    LICH_COM_DOR_NAS_COSTAS("Lich com Dor nas Costas", 11),
    DRAGAO_DE_KOMODO_COM_BAFO_DE_FOGO("Dragão de Komodo com Bafo de Fogo", 12),
    BARDO_DESAFINADO("Bardo Desafinado", 12),
    MONSTRO_INDESCRITIVEL("Monstro Indescritível", 13),
    GRIFO_COM_LABIRINTITE("Grifo com Labirintite", 14),
    ELEMENTAL_GIGANTE_DE_POEIRA("Elemental Gigante de Poeira", 15),
    BEHOLDER_COM_ASTIGMATISMO("Beholder com Astigmatismo", 16),
    ADVOGADO_DO_DIABO("Advogado do Diabo (Literalmente)", 17),
    GERENTE_DE_PROJETOS_DE_MASMORRA("Gerente de Projetos de Masmorra", 18),
    DRAGAO_DE_PLUTONIO("Dragão de Plutônio", 20);

    private final String nome;
    private final int nivel;

    Monstro(String nome, int nivel) {
        this.nome = nome;
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }
}