package Experiment_1;

import sun.dc.pr.PRError;

import java.util.ArrayList;
import java.util.List;

class Process {
    public String name = null;
    public int TArrive = 0;
    public int TService = 0;
    public int TComplete = 0;
    public int Ti = 0;
    public double W = 0;
    boolean isDealed = false;

    public Process(String name, int TArrive, int TService) {
        this.name = name;
        this.TArrive = TArrive;
        this.TService = TService;
    }

    public void setTComplete(int TComplete) {
        this.TComplete = TComplete;
    }

    public void setW(double w) {
        W = w;
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", TArrive=" + TArrive +
                ", TService=" + TService +
                ", TComplete=" + TComplete +
                ", Ti=" + Ti +
                ", W=" + W +
                '}';
    }
}

public class Scheduling {
    public List<Process> list = new ArrayList<>();

    public void add(String name,int t1, int t2) {
        list.add(new Process(name, t1, t2));
    }

    public Process find(String name) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).name.equals(name)) {
                return list.get(i);
            }
        }
        return null;
    }
    public Process find(int TServiceMin) {
        for (Process aList : list) {
            if (aList.TService == TServiceMin && !aList.isDealed) {
                aList.isDealed = true;
                return aList;
            }
        }
        return null;
    }
    public void remove(String name) {
        list.remove(find(name));
    }

    public void achieve() {
        List<Process> deal = new ArrayList<>(list);
        int t = 0;
        while (!deal.isEmpty()) {
            int TServiceMin = deal.get(0).TService;
            Process toRemove = null;

            for (Process goal : deal) {
                if (goal.TArrive <= t && TServiceMin >= goal.TService) {
                    TServiceMin = goal.TService;
                    toRemove = goal;
                }
            }
            Process process = find(TServiceMin);
            process.TComplete = t + TServiceMin;
            process.Ti = process.TComplete - process.TArrive ;
            process.W = (double)process.Ti / process.TService;
            t = process.TComplete;
            deal.remove(toRemove);
        }


    }

    public void display() {
        for (Process aList : list) {
            System.out.println(aList.toString());
        }
    }


}
