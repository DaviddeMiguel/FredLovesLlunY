package com.meslize.fredloveslluny.ui.lluny.list.view;

import com.meslize.fredloveslluny.model.LlunyModel;
import java.util.List;

public interface LlunyListView {
  void showItems(List<LlunyModel> items);

  void startForResultDetectListScreen();
}
