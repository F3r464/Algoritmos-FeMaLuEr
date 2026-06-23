package Practica11.Interfaz.src;

public class HashTable {

    private Register[] table;
    private HashFunction hashFunction;
    private LinearProbing probing;

    private MyList<Step> steps;

    public HashTable(int size) {
        table = new Register[size];
        hashFunction = new HashFunction(size);
        probing = new LinearProbing();
        steps = new MyList<>();
    }

    public MyList<Step> insertVisual(Register reg) {

        steps.clear();

        int key = reg.getId();
        int hash = hashFunction.hashDivision(key);

        steps.add(new Step("ID " + key + " → hash = " + key + " % " + table.length + " = " + hash, hash,reg));

        int i = 0;

        while (i < table.length) {

            int pos = probing.resolve(hash, i, table.length);

            if (table[pos] == null) {

                table[pos] = reg;

                steps.add(new Step(
                        "Posición " + pos + " libre → INSERTADO",
                        pos,
                        reg
                ));

                return steps;
            }

            steps.add(new Step(
                    "Colisión en posición " + pos + " → probando siguiente",
                    pos,
                    reg
            ));

            i++;
        }

        steps.add(new Step("Tabla llena", -1, reg));

        return steps;
    }

    public Register[] getTable() {
        return table;
    }

    public Step getStep(MyList<Step> steps, int index) {
        return steps.get(index);
    }
}