package org.elliotnash.razercraft.core;

import org.elliotnash.razercraft.core.device.DeviceManager;
import org.elliotnash.razercraft.core.lighting.Color;
import org.elliotnash.razercraft.core.lighting.LightingManager;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.connections.impl.DBusConnection.DBusBusType;
import org.freedesktop.dbus.exceptions.DBusException;


import java.util.ArrayList;

public class RazerController {

  public DBusConnection connection = null;
  public LightingManager lightingManager;
  public Integer activeSlot = null;
  public ArrayList<Integer> filledSlots = new ArrayList<>(9);
  public int refreshRate;

  public RazerController(int refreshRate) {

    this.refreshRate = refreshRate;

    try {
      connection = DBusConnection.getConnection(DBusBusType.SESSION);
    } catch (DBusException e) {
      e.printStackTrace();
    }

    DeviceManager deviceManager = new DeviceManager(connection);

    lightingManager = new LightingManager(connection, deviceManager.getDevices().get(0));

    drawThread.start();

  }

  long lastDraw = System.currentTimeMillis();

  Thread drawThread = new Thread(() -> {
      while (true) {
        //limit draw calls to refresh rate
        if (lastDraw > System.currentTimeMillis() - refreshRate) {
          return;
        }
        lastDraw = System.currentTimeMillis();

        for (int i = 0; i < lightingManager.matrix.length; i++) {
          for (int j = 0; j < lightingManager.matrix[i].length; j++) {
            // matrix[i][j] = new int[] { 15, 50, 0 };
          }
        }

        // colours
        Color wasdColor = Color.LAVENDER;
        Color ctrlShiftColor = Color.PURPLE;
        Color emptySlotColor = Color.BLUE;
        Color filledSlotColor = Color.LAVENDER;
        Color activeSlotColor = Color.WHITE;

        // wasd
        lightingManager.matrix[2][3] = wasdColor;
        lightingManager.matrix[3][2] = wasdColor;
        lightingManager.matrix[3][3] = wasdColor;
        lightingManager.matrix[3][4] = wasdColor;
        //ctrl+shift
        lightingManager.matrix[4][1] = ctrlShiftColor;
        lightingManager.matrix[5][1] = ctrlShiftColor;

        //hotbar
        for (int i = 2; i < 11; i++) {
          lightingManager.matrix[1][i] = emptySlotColor;
        }

        //filled hotbar slots
        for (int slot : filledSlots) {
          lightingManager.matrix[1][slot + 2] = filledSlotColor;
        }

        // active item
        if (activeSlot != null) {
          // then we draw last pressed key
          lightingManager.matrix[1][activeSlot + 2] = activeSlotColor;
        }

        lightingManager.drawMatrix();

        //lets sleep this
        try {
          Thread.sleep(refreshRate);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
  });

}
