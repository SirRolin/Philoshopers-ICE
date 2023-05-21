package philosophers_ice;


import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Scanner;

public class CombatScene extends Thread {
    public static CombatScene currentlyRunningCS;
    private final ArrayList<Enemy> enemies;
    private final Player currentPlayer;
    private int maxInitiative;
    public boolean isRunning;
    private String text = "";
    int turn = 1;

    public CombatScene(Player player, ArrayList<Enemy> enemies) {
        this.currentPlayer = player;
        this.enemies = enemies;
    }

    public void startCombat() {
        currentlyRunningCS = this;
        start();
    }

    @Override
    public void run() {
        isRunning = true;
        currentPlayer.initiative = currentPlayer.getMaxInitiative();
        maxInitiative = currentPlayer.initiative;
        for (Enemy enemy : enemies) {
            int tmpEnemyInitative = enemy.getInitiative();
            if (tmpEnemyInitative > maxInitiative) {
                maxInitiative = tmpEnemyInitative;
            }
        }
        if(currentPlayer.inventory.getEquippedWeaponMainHand() instanceof Ranged){
            System.out.println("since you have a ranged weapon you get to attack 1 enemy before combat starts.");
            Enemy choosenEnemy = chooseEnemyToAttack();
            System.out.println("You attacked: " + choosenEnemy.getName() + " and dealt: " + choosenEnemy.takeDamage(currentPlayer.attack()) + " damage");
            System.out.println(choosenEnemy.getName() + " now have " + choosenEnemy.getHp() + " health remaining");
            if (choosenEnemy.getHp() <= 0) {
                System.out.println("The " + choosenEnemy.getName() + " died and you loot the following items:");
                for (Item i : choosenEnemy.droppedLoot()) {
                    System.out.print("- " + i.getName() + "\n");
                }
                System.out.println("from the remains.");
                currentPlayer.getLoot(choosenEnemy.droppedLoot());// PLACEHOLDER!!
                enemies.remove(choosenEnemy);
            }
        }
        if(!enemies.isEmpty()){
            System.out.println("--- You entered combat! ---");
        }
        combat();
    }

    private void combat() {
        while (currentPlayer.getHP() > 0 && enemies.size() > 0 && turn < 99) {
            if (currentPlayer.initiative >= getHigestInitiativeEnemy().getInitiative()) {
                if (currentPlayer.initiative >= maxInitiative) {
                    chooseActionPlayer();
                }
            } else {
                if (getHigestInitiativeEnemy().getInitiative() >= maxInitiative) {
                    System.out.println(getHigestInitiativeEnemy().getName() + " attacked you for: " + currentPlayer.takeDamage(getHigestInitiativeEnemy().attack()) + " damage.");
                    System.out.println("You have: " + currentPlayer.getHP() + " health remaining after the attack.");
                    System.out.println("-----------------------------------");
                    getHigestInitiativeEnemy().updateInitiative(-maxInitiative);
                }
            }

            if (currentPlayer.initiative < maxInitiative && enemyNotAboveMaxInitiative()) {
                currentPlayer.initiative += currentPlayer.getMaxInitiative();
                for (Enemy e : enemies) {
                    e.updateInitiative(e.getMaxInitiative());
                }
                situationUpdate();
            }
        }
        if (currentPlayer.getHP() <= 0) {
            System.out.println("YOU DIED!!! :(");
            endGame();
        }
        endCombat();
    }

    private void situationUpdate() {
    }

    private void chooseActionPlayer() {
        System.out.println("Its now turn: " + turn);
        System.out.println("-----------------------------------");
        if (turn < 2) {
            System.out.println("You have the following choices: ");
            System.out.println("1. Attack an enemy");
            System.out.println("2. Cast a spell");
            System.out.println("3. Use Item");
            System.out.println("4. Use Recover");
            System.out.println("-----------------------------------");
        }
        int input = retrieveInput();
        switch (input) {
            case 1 -> {
                Enemy choosenEnemy = chooseEnemyToAttack();
                System.out.println("You attacked: " + choosenEnemy.getName() + " and dealt: " + choosenEnemy.takeDamage(currentPlayer.attack()) + " damage");
                System.out.println(choosenEnemy.getName() + " now have " + choosenEnemy.getHp() + " health remaining");
                if (choosenEnemy.getHp() <= 0) {
                    System.out.println("The " + choosenEnemy.getName() + " died and you loot the following items:");
                    int sizeOfEnemyInventory = choosenEnemy.droppedLoot().size();
                    for (Item i : choosenEnemy.droppedLoot()) {
                        System.out.print("- " + i.getName() + "\n");
                    }
                    System.out.println("from the remains.");
                    currentPlayer.getLoot(choosenEnemy.droppedLoot());// PLACEHOLDER!!
                    enemies.remove(choosenEnemy);
                }
                currentPlayer.initiative -= maxInitiative;
                turn++;
                break;
            }
            case 2 -> {
                // WORK IN PROGESS
                System.out.println("Work in progress please choose an other option");
                chooseActionPlayer();
            }
            case 3 -> {
                // WORK IN PROGESS
                System.out.println("Work in progress please choose an other option!");
                chooseActionPlayer();
            }
            case 4 -> {
                System.out.println("You sit down and rest, recovering: " + currentPlayer.recover() + " hp");
                System.out.println("After your rest, your health is now: " + currentPlayer.getHP());
                turn++;
            }
            default -> {
                System.out.println("Did not recognise command, try again");
                chooseActionPlayer();
            }
        }

    }

    public void endCombat() {
        System.out.println("woohoo it worked?");
    }

    public void endGame() {
        isRunning = false;
    }

    private boolean enemyNotAboveMaxInitiative() {
        boolean tmp = false;
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getInitiative() < maxInitiative) {
                tmp = true;
            }
        }
        return tmp;
    }

    private Enemy getHigestInitiativeEnemy() {
        Enemy highestInitiativeEnemy = enemies.get(0);
        int tmpInitiative = highestInitiativeEnemy.getInitiative();
        for (int i = 1; i < enemies.size(); i++) {
            if (enemies.get(i).getInitiative() > tmpInitiative) {
                highestInitiativeEnemy = enemies.get(i);
                tmpInitiative = enemies.get(i).getInitiative();
            }
        }
        return highestInitiativeEnemy;
    }

    private Enemy chooseEnemyToAttack() {
        if (enemies.size() == 1) {
            return enemies.get(0);
        }
        System.out.println("which enemy do you want to attack? 1 - " + enemies.size());
        int input = retrieveInput();
        if (input > enemies.size()) {
            System.out.println("there's not that many enemies");
            return chooseEnemyToAttack();
        }
        return enemies.get(input - 1);
    }

    private int retrieveInput() {
        while(this.text.equals("")){
            try {
                //noinspection BusyWait
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try{
            String localHolder = this.text;
            this.text = "";
            return Integer.parseInt(localHolder.trim());
        } catch (NumberFormatException e){
            return -1;
        }
    }

    public static boolean setInput(String text){
        if(currentlyRunningCS != null && currentlyRunningCS.isRunning){
            while(!currentlyRunningCS.text.equals("")){
                try {
                    //noinspection BusyWait
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            currentlyRunningCS.text = text;
            return true;
        }
        return false;
    }
}
