package edu.senai.guilherme.simuladorrpg.model;

/**
 * Define os "slots" onde os equipamentos podem ser usados.
 */

public enum TipoEquipamento {
    CABECAL,      // Headgear
    ARMADURA,     // Armor
    CALCADO,      // Footgear
    MAO,          // Hand (para armas de 1 ou 2 mãos)
    ITEM_GERAL    // Itens que não ocupam um slot específico
}