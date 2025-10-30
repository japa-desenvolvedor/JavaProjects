// Monstro.java — ATUALIZADO
package edu.senai.pietro.simuladorrpg.classes;

public enum Monstro {
    ORC_DE_TELEMARKETING("Orc de Telemarketing", 3),
    DRAGAO_LADRAO("Dragão Ladrão", 8),
    MARCO_FRUSTADO("Marco Frustado", 2),
    MAGO_DA_REQUISICAO_DO_INFERNO("Mago da requisição do inferno", 4),
    BRUXO_DO_NEXT("Bruxo do next", 5),
    BULL_BOÇALZINHO("Bull Boçalzinho", 1);

    private final String nome;
    private final int nivel;
    private int vida; // ← vida instanciável por cópia (veja abaixo)

    Monstro(String nome, int nivel) {
        this.nome = nome;
        this.nivel = nivel;
        this.vida = nivel * 8; // vida base = nível * 8
    }

    // Método para criar uma INSTÂNCIA COM VIDA (enum é constante, então usamos clone lógico)
    public MonstroInstancia criarInstancia() {
        return new MonstroInstancia(this.nome, this.nivel, this.nivel * 8);
    }

    // Getters
    public String getNome() { return nome; }
    public int getNivel() { return nivel; }
    public int getVidaBase() { return nivel * 8; }

    // Classe auxiliar para permitir combate com estado (vida mutável)
    public static class MonstroInstancia {
        private String nome;
        private int nivel;
        private int vida;

        public MonstroInstancia(String nome, int nivel, int vida) {
            this.nome = nome;
            this.nivel = nivel;
            this.vida = vida;
        }

        public void receberDano(int dano) {
            this.vida -= dano;
            if (this.vida < 0) this.vida = 0;
            System.out.println(this.nome + " sofreu " + dano + " de dano. Vida restante: " + this.vida);
        }

        public boolean estaVivo() {
            return this.vida > 0;
        }

        public int getForca() {
            return this.nivel; // ou pode ter um cálculo mais complexo
        }

        // Getters
        public String getNome() { return nome; }
        public int getNivel() { return nivel; }
        public int getVida() { return vida; }
    }
}