package com.meslize.fredloveslluny.ui.scan.list.view;

import com.meslize.fredloveslluny.model.LlunyModel;
import java.util.List;

public interface ScanListView {
  void showItems(List<LlunyModel> items);

  void startEditLlunyScreen();
}
