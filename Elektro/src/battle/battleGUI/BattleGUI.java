package battle.battleGUI;

import battle.Battle;
import battle.battleGUI.actions.RanButton;
import battle.battleGUI.actions.attacks.LowAttackAction;
import battle.battleGUI.actions.attacks.PopUpFight;
import battle.battleGUI.actions.attacks.SpecialAttackAction;
import battle.battleGUI.actions.bag.BagUpdate;
import battle.battleGUI.actions.bag.ChangeWeapon;
import battle.battleGUI.actions.bag.PopUpBag;
import battle.battleGUI.actions.heal.BandageHealing;
import battle.battleGUI.actions.heal.FoodHealing;
import battle.battleGUI.actions.heal.KitHealing;
import battle.battleGUI.actions.heal.PopUpHeal;
import utilities.UpdateBattle;
import utilities.arcadeFont;

import javax.swing.*;
import java.awt.*;

/**
 * this is the Battle GUI system
 */

public class BattleGUI extends JFrame {
    private JProgressBar playerHP;
    private JProgressBar enemyHP;
    private JButton attackButton;
    private JButton healButton;
    private JButton escapeButton;
    private JButton bagButton;
    private Battle battle;


    /**
     * public constructor of the battle GUI
     * the panel start just created the battle GUI
     * @param battle the battle we use to build the GUI
     */
    public BattleGUI(Battle battle) {
        this.battle = battle;
        initGUI();
    }

    /**
     * Build of the GUI with all the components (JLabel, JButton, JPanel, etc.)
     */
    private void initGUI() {
        // Test
        setUndecorated(true);
        arcadeFont font = new arcadeFont("res/fonts/ARCADE_N.TTF",20.0f,Font.BOLD);
        Font arcadeFont = font.getFont();
        Scenario backgroundPanel = new Scenario(battle.getBackgroundPath());
        backgroundPanel.setLayout(null);
        //Message in game label
        JLabel status = new JLabel(UpdateBattle.originalState());
        status.setBounds(100, 410, 700, 40);
        status.setForeground(Color.white);
        status.setFont(arcadeFont);
        backgroundPanel.add(status);
        //Another message in game label
        JLabel enemyStatus = new JLabel("");
        enemyStatus.setBounds(100,430,700,40);
        enemyStatus.setForeground(Color.white);
        enemyStatus.setFont(arcadeFont);
        backgroundPanel.add(enemyStatus);
        //player
        playerHP = new JProgressBar(0, 100);
        playerHP.setValue(battle.getPlayer().getHP());
        playerHP.setBounds(358, 310, 250, 25);
        playerHP.setForeground(Color.cyan);
        backgroundPanel.add(playerHP);
        //enemy
        enemyHP = new JProgressBar(0, battle.getEnemy().getHP());
        enemyHP.setValue(battle.getEnemy().getHP());
        enemyHP.setBounds(100, 70, 200, 30);
        enemyHP.setForeground(Color.cyan);
        backgroundPanel.add(enemyHP);
        //contentPane
        JPanel contentPane = new JPanel(new BorderLayout());
        this.setContentPane(contentPane);
        //Botones
        JPanel GameplayPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        attackButton = new JButton("Attack");
        healButton = new JButton("Heal");
        escapeButton = new JButton("Escape");
        bagButton = new JButton("Bag");
        GameplayPanel.setBackground(Color.black);
        GameplayPanel.add(attackButton);
        GameplayPanel.add(healButton);
        GameplayPanel.add(bagButton);
        GameplayPanel.add(escapeButton);

        //add panels
        contentPane.add(GameplayPanel, BorderLayout.SOUTH);
        contentPane.add(backgroundPanel, BorderLayout.CENTER);

        //Custom button
        attackButton.setBackground(Color.red);
        attackButton.setForeground(Color.white);
        attackButton.setFont(arcadeFont);
        healButton.setBackground(Color.green);
        healButton.setForeground(Color.white);
        healButton.setFont(arcadeFont);
        bagButton.setBackground(Color.orange);
        bagButton.setForeground(Color.white);
        bagButton.setFont(arcadeFont);
        escapeButton.setBackground(Color.blue);
        escapeButton.setForeground(Color.white);
        escapeButton.setFont(arcadeFont);
        //Jdialog de ataques
        JDialog fightDialog = new JDialog(this, battle.getPlayer().getWeapon().nameWeapon());
        fightDialog.setModal(true);
        JPanel fightOptionPanel = new JPanel();
        fightOptionPanel.setLayout(new GridLayout(0, 1));
        JLabel weaponDescription = new JLabel(battle.getPlayer().getWeapon().getDescription());
        JButton attack1 = new JButton(battle.getPlayer().getWeapon().lowAttackInfo());
        JButton attack2 = new JButton(battle.getPlayer().getWeapon().specialAttackInfo());
        fightOptionPanel.add(weaponDescription, BorderLayout.NORTH);
        fightOptionPanel.add(attack1);
        fightOptionPanel.add(attack2);
        fightDialog.getContentPane().add(fightOptionPanel);
        fightDialog.pack();

        //Jdialog de Curacion
        JDialog healDialog = new JDialog(this, "Healing Options");
        healDialog.setModal(true);
        JPanel healOptionPanel = new JPanel();
        healOptionPanel.setLayout(new GridLayout(0, 1));
        JLabel healDescrp = new JLabel("Which item want to use: ");
        JButton food = new JButton("Food: " + battle.cantFood());
        JButton bandage = new JButton("Bandage: " + battle.cantBandage());
        JButton kit = new JButton("Kit: " + battle.cantKit());
        healOptionPanel.add(healDescrp, BorderLayout.NORTH);
        healOptionPanel.add(food);
        healOptionPanel.add(bandage);
        healOptionPanel.add(kit);
        healDialog.getContentPane().add(healOptionPanel);
        healDialog.pack();

        //Jdialog de Bag
        JDialog bagDialog = new JDialog(this, "Bag");
        bagDialog.setModal(true);
        JPanel bagOptionPanel = new JPanel();
        bagOptionPanel.setLayout(new GridLayout(0, 1));
        JLabel bagDescription = new JLabel("Which weapon want to use");
        JButton weapon1 = new JButton();
        JButton weapon2 = new JButton();
        JButton weapon3 = new JButton();
        bagOptionPanel.add(bagDescription, BorderLayout.NORTH);
        BagUpdate.weaponsInBag(weapon1, weapon2, weapon3, battle);
        if (!weapon1.getText().isEmpty()) {
            bagOptionPanel.add(weapon1);
        }
        if (!weapon2.getText().isEmpty()) {
            bagOptionPanel.add(weapon2);
        }
        if (!weapon3.getText().isEmpty()) {
            bagOptionPanel.add(weapon3);
        }
        bagDialog.getContentPane().add(bagOptionPanel);
        bagDialog.pack();

        //acciones de los botones
        escapeButton.addActionListener(new RanButton(battle, playerHP, enemyHP, status,this,enemyStatus));
        attackButton.addActionListener(new PopUpFight(fightDialog));
        healButton.addActionListener(new PopUpHeal(healDialog));
        bagButton.addActionListener(new PopUpBag(bagDialog));
        attack1.addActionListener(new LowAttackAction(battle, playerHP, enemyHP, status, fightDialog, this,enemyStatus));
        attack2.addActionListener(new SpecialAttackAction(battle, playerHP, enemyHP, status, fightDialog, this,enemyStatus));
        food.addActionListener(new FoodHealing(battle, playerHP, enemyHP, healDialog, food, status));
        bandage.addActionListener(new BandageHealing(battle, playerHP, enemyHP, healDialog, bandage, status));
        kit.addActionListener(new KitHealing(battle, playerHP, enemyHP, healDialog, kit, status));
        //
        weapon1.addActionListener(new ChangeWeapon(battle, weapon1, bagDialog, weaponDescription));
        weapon2.addActionListener(new ChangeWeapon(battle, weapon2, bagDialog, weaponDescription));
        weapon3.addActionListener(new ChangeWeapon(battle, weapon3, bagDialog, weaponDescription));
        //Ventana
        setTitle("Battle!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        //Custom
    }

//    public static void main(String[] args) {
//        StateOfBattle state = StateOfBattle.readyAndGo;
//        EntityFactory eF;
//        Entity player;
//        Entity enemy;
//        WeaponFactory wF = new ElectricGunFactory();
//        AttackItem weapon = wF.createWeapon();
//        eF = new SentinelFactory();
//        enemy = eF.createEntity();
//        eF = new PlayerFactory();
//        player = eF.createEntity();
//        player.setWeapon(weapon);
//        player.getWeaponsBag().add(weapon);
//        wF = new BatFactory();
//        weapon = wF.createWeapon();
//        player.getWeaponsBag().add(weapon);
//        Item food;
//        HealingFactory hF;
//        hF = new FoodFactory();
//        food = hF.createHealing();
//        player.getHealingsItems().add((HealerItem) food);
//        Battle bt = new Battle(state,player,enemy,true);
//        bt.gameplay();
//
//    }
}
