package com.jtsoft.letmedo.adapter.base;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseListAdapter<T> extends BaseAdapter {

    protected Context context;
    protected List<T> list;

    public BaseListAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<T>();
    }

    public boolean addAll(List<? extends T> list) {
        return this.list.addAll(list);
    }

    public boolean add(T item) {
        return this.list.add(item);
    }

    public void clear() {
        this.list.clear();
    }

    public int getCount() {
        return list == null ? 0 : list.size();
    }

    public T getItem(int i) {
        return this.list.get(i);
    }

    public long getItemId(int id) {
        return id;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isEmpty() {
        return list == null ? true : list.isEmpty();
    }

    public T remove(int i) {
        return list.remove(i);
    }

    public void addItem(T t) {
        this.list.add(t);
    }

    public List<T> getList() {
        return list;
    }
}
