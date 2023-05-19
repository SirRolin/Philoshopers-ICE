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

        while(currentPlayer.getHP()>0 && enemies.size()>0 && turn < 99){
            if(tmpPlayerInitiative >= getHigestInitiativeEnemy().getInitiative()){
                chooseActionPlayer();
            }else{
               currentPlayer.takeDamage(getHigestInitiativeEnemy().attack());
               getHigestInitiativeEnemy().updateInitiative(-maxInitiative);
            }

            if(tmpPlayerInitiative < maxInitiative && enemyNotAboveMaxInitiative()){
                tmpPlayerInitiative += maxInitiative;
                for (Enemy e: enemies) {
                    e.updateInitiative(maxInitiative);
                }
            }
        }
        if(currentPlayer.getHP() <= 0){
            endGame();
        }
        endCombat();
    }

    private void chooseActionPlayer() {
        System.out.println("You have the following choices: ");
        System.out.println("1. Attack an enemy");
        System.out.println("2. Cast a spell");
        System.out.println("3. Use Item");
        System.out.println("4. Use Recover");
        Scanner scan = new Scanner(System.in);
            int input = scan.nextInt();
            scan.nextLine();
            switch (input) {
                case 1 -> {
                    Enemy choosenEnemy = chooseEnemyToAttack();
                    choosenEnemy.takeDamage(currentPlayer.attack());
                    if (choosenEnemy.getHp() <= 0) {
                        currentPlayer.getLoot(choosenEnemy.droppedLoot()); // PLACEHOLDER!!
                        enemies.remove(choosenEnemy);
                    }
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
                    System.out.println("You sit down and rest recovering hp");
                    currentPlayer.recover();
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
        int input = scan.nextInt();
        scan.nextLine();
        if(input > enemies.size()){
            System.out.println("there's not that many enemies");
            return chooseEnemyToAttack();
        }
        return enemies.get(input - 1);
    }

}
