package philosophers_ice;


import java.util.ArrayList;
import java.util.Scanner;

public class CombatScene {
    private ArrayList<Enemy> enemies;
    private Player currentPlayer;
    private int maxInitiative;
    public CombatScene(Player player,ArrayList<Enemy> enemies){
        this.currentPlayer = player;
        this.enemies = enemies;
    }


    public void startCombat(Player currentPlayer, ArrayList<Enemy> enemies){
        int enemyInitative = 0;
        for (Enemy enemy: enemies) {
            int tmpEnemyInitative = enemy.getInitiative();
            if(tmpEnemyInitative > enemyInitative){
                enemyInitative = tmpEnemyInitative;
            }
        }
        maxInitiative = Math.max(currentPlayer.getInitiative(),enemyInitative);
        combat(currentPlayer,enemies);
    }
    public void combat(Player currentPlayer,ArrayList<Enemy> enemies){
        int turn = 1;
        while(currentPlayer.getHP()<=0 || enemies.size()<=0|| turn == 99){
            if(currentPlayer.getInitiative() < maxInitiative){
                currentPlayer.updateIniative(maxInitiative);
                turn++;
            }
            if(enemyNotAboveMaxInitiative(enemies)){
                for (Enemy e: enemies) {
                    if(e.getInitiative() < maxInitiative){
                        e.updateInitiative(maxInitiative);
                        turn++;
                    }
                }
            }
            if(currentPlayer.initiative > getHigestInitiativeEnemy(enemies).getInitiative()){
                Enemy choosenEnemy = chooseEnemyToAttack(enemies);
                choosenEnemy.updateHP(-currentPlayer.attack());
                if(choosenEnemy.getHp() >= 0){
                    currentPlayer.getLoot(choosenEnemy.droppedLoot()); // PLACEHOLDER!!
                    enemies.remove(choosenEnemy);
                }
            }else{
               currentPlayer.increaseHP(-getHigestInitiativeEnemy(enemies).attack());
            }
        }
        if(currentPlayer.getHP() <= 0){
            endGame();
        }
        endCombat();
    }
    public void endCombat(){

    }
    public void endGame(){
        
    }
    private boolean enemyNotAboveMaxInitiative(ArrayList<Enemy> enemies){
        boolean tmp = false;
        for(int i = 0;i < enemies.size();i++){
            if(enemies.get(i).getInitiative() < maxInitiative){
              tmp = true;
            }
        }
        return tmp;
    }
    private Enemy getHigestInitiativeEnemy(ArrayList<Enemy> enemies){
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
    private Enemy chooseEnemyToAttack(ArrayList<Enemy> enemies){
        System.out.println("which enemy do you want to attack? 1 - " + enemies.size());
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        scan.nextInt();
        if(input > enemies.size()){
            System.out.println("there's not that many enemies");
            return chooseEnemyToAttack(enemies);
        }
        return enemies.get(input - 1);
        /*switch (input){
            case 1 -> {
                choosenEnemy = enemies.get(0);
            }
            case 2 ->  {
                if(enemies.size()<2){
                    System.out.println("there's less than 2 enemies");
                   return chooseEnemyToAttack(enemies);
                }
                choosenEnemy = enemies.get(1);
            }
            case 3 ->{
                if(enemies.size()<3){
                    System.out.println("there's less than 3 enemies");
                    return chooseEnemyToAttack(enemies);
                }
                choosenEnemy = enemies.get(2);
            }
        }*/
    }

}
