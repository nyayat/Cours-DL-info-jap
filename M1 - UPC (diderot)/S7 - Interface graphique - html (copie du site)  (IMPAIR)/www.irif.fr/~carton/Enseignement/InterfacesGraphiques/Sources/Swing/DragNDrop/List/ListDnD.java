// -*- coding: utf-8 -*-
// Time-stamp: <ListDnD.java  21 oct 2020 08:51:55>

// Inspiré de http://zetcode.com/javaswing/draganddrop/

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;

public class ListDnD extends JFrame {
    private JTextField field;
    private DefaultListModel model;
    public ListDnD() {
        //  Titre
        setTitle("List Drag & Drop");
        // Action à faire lorsque la fenêtre est fermée.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Composants
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(180, 150));
        model = new DefaultListModel();
        JList list = new JList(model);
        list.setDropMode(DropMode.INSERT);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setTransferHandler(new ListHandler());
        field = new JTextField(15);
        field.setDragEnabled(true);
        scrollPane.getViewport().add(list);
        // Mise en place des éléments dans le panneau principal
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(field)
                .addComponent(scrollPane)
        );
        gl.setVerticalGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(field)
                .addComponent(scrollPane)
        );
        pack();
        //  Positionnement relatif au lancement
        setLocationRelativeTo(null);
    }
    // Gestionnaire de transfert
    private class ListHandler extends TransferHandler {
        public boolean canImport(TransferSupport support) {
            if (!support.isDrop()) {
                return false;
            }
            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
        }
        public boolean importData(TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }
            Transferable transferable = support.getTransferable();
            String line;
            try {
                line = (String) transferable.getTransferData(DataFlavor.stringFlavor);
            } catch (Exception exception) {
                return false;
            }
            JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
            int index = dl.getIndex();
            for (String item : line.split("[,\\s]")) {
                if (!item.isEmpty())
                    model.add(index++, item.trim());
            }
            return true;
        }
    }
    // Main
    public static void main(String[] args) {
        ListDnD ldnd = new ListDnD();
        ldnd.setVisible(true);
    }
}
