package philosophers_ice;


import java.util.ArrayList;
import java.util.Scanner;

public class CombatScene {
    private ArrayList<Enemy> enemies;
    private Player currentPlayer;
    private int tmpPlayerInitiative;
    private int maxInitiative;
    int turn = 1;
    public CombatScene(Player player,ArrayList<Enemy> enemies){
        this.currentPlayer = player;
        this.enemies = enemies;
    }


    public void startCombat(){
        this.tmpPlayerInitiative = currentPlayer.getInitiative();
        maxInitiative = tmpPlayerInitiative;
        for (Enemy enemy: enemies) {
            int tmpEnemyInitative = enemy.getInitiative();
            if(tmpEnemyInitative > maxInitiative){
                maxInitiative = tmpEnemyInitative;
            }
        }
//        maxInitiative = Math.max(tmpPlayerInitiative,enemyInitative);
        combat();
    }
    private void combat(){
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|||||||||||||You entered combat!|||||||||||||");
        while(currentPlayer.getHP()>0 && enemies.size()>0 && turn < 99){
            if(tmpPlayerInitiative >= getHigestInitiativeEnemy().getInitiative()){
                chooseActionPlayer();
            }else{
                System.out.println(getHigestInitiativeEnemy().getName()+" attacked you for: "+currentPlayer.takeDamage(getHigestInitiativeEnemy().attack())+"damage");
                System.out.println("You have: "+currentPlayer.getHP()+" health remaining after the attack");
                System.out.println("-----------------------------------");
                getHigestInitiativeEnemy().updateInitiative(-maxInitiative);
            }

            if(tmpPlayerInitiative < maxInitiative && enemyNotAboveMaxInitiative()){
                tmpPlayerInitiative += maxInitiative;
                for (Enemy e: enemies) {
                    e.updateInitiative(maxInitiative);
                }
            }
            situationUpdate();
        }
        if(currentPlayer.getHP() <= 0){
            System.out.println("YOU DIED!!! :(");
            endGame();
        }
        endCombat();
    }

    private void situationUpdate() {
    }

    private void chooseActionPlayer() {
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("Its now turn: "+turn);
        System.out.println("-----------------------------------");
        System.out.println("You have the following choices: ");
        System.out.println("1. Attack an enemy");
        System.out.println("2. Cast a spell");
        System.out.println("3. Use Item");
        System.out.println("4. Use Recover");
        System.out.println("-----------------------------------");
        Scanner scan = new Scanner(System.in);
            int input = 1; //scan.nextInt();
            //scan.nextLine();
            switch (input) {
                case 1 -> {
                    Enemy choosenEnemy = chooseEnemyToAttack();
                    System.out.println("You attacked: "+ choosenEnemy.getName() + " and delt: " + choosenEnemy.takeDamage(currentPlayer.attack())+" damage");
                    System.out.println(choosenEnemy.getName() + " now have " + choosenEnemy.getHp() +" health remaining");
                    if (choosenEnemy.getHp() <= 0) {
                        System.out.println("The "+choosenEnemy.getName() +" died and you loot the following items:");
                        int sizeOfEnemyInventory = choosenEnemy.droppedLoot().size();
                        for (Item i:choosenEnemy.droppedLoot()) {
                            System.out.print("- "+i.getName()+"\n");
                        }
                        System.out.println("from the remains.");
                        currentPlayer.getLoot(choosenEnemy.droppedLoot());// PLACEHOLDER!!
                        enemies.remove(choosenEnemy);
                    }
                    System.out.println("-----------------------------------");
                    tmpPlayerInitiative-= maxInitiative;
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
                    System.out.println("You sit down and rest, recovering: "+currentPlayer.recover()+" hp");
                    System.out.println("After your rest, your health is now: "+currentPlayer.getHP());
                    turn++;
                }
                default -> {
                    System.out.println("Did not recognise command, try again");
                    chooseActionPlayer();
                }
            }

    }

    public void endCombat(){
        System.out.println("woohoo it worked?");
    }
    public void endGame(){
        
    }
    private boolean enemyNotAboveMaxInitiative(){
        boolean tmp = false;
        for(int i = 0;i < enemies.size();i++){
            if(enemies.get(i).getInitiative() < maxInitiative){
              tmp = true;
            }
        }
        return tmp;
    }
    private Enemy getHigestInitiativeEnemy(){
        Enemy highestInitiativeEnemy = enemies.get(0);
        int tmpInitiative = highestInitiativeEnemy.getInitiative();
        for(int i = 1;i < enemies.size();i++){
            if(enemies.get(i).getInitiative() > tmpInitiative){
                highestInitiativeEnemy = enemies.get(i);
                tmpInitiative = enemies.get(i).getInitiative();
            }
        }
        return highestInitiativeEnemy;
    }
    private Enemy chooseEnemyToAttack(){
        if(enemies.size() == 1){
            return enemies.get(0);
        }
        System.out.println("which enemy do you want to attack? 1 - " + enemies.size());
        Scanner scan = new Scanner(System.in);
        /*int input = scan.nextInt();
        scan.nextLine();
        if(input > enemies.size()){
            System.out.println("there's not that many enemies");
            return chooseEnemyToAttack();
        }*/
        return enemies.get(1); //enemies.get(input - 1);
    }

}
