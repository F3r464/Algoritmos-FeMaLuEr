package ejercicios;

public class Ejercicio6 {

    static class Session {
        String token;
        String username;
        String role;
        long   expiresAt; // Marca de tiempo absoluta UNIX expresada en milisegundos

        Session(String token, String username, String role, long expiresAt) {
            this.token     = token;
            this.username  = username;
            this.role      = role;
            this.expiresAt = expiresAt;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiresAt; // Evalúa si el reloj del sistema sobrepasó la tolerancia vital fijada
        }

        @Override
        public String toString() {
            return "[" + token + " | " + username + " | " + role + " | expira en " + (expiresAt - System.currentTimeMillis()) + "ms]";
        }
    }

    static class ChainNode {
        Session session;
        ChainNode next;
        ChainNode(Session s) { this.session = s; this.next = null; }
    }

    static class Chain {
        ChainNode head;
        int size;

        Chain() { head = null; size = 0; }

        void add(Session s) {
            ChainNode node = new ChainNode(s);
            node.next = head; // Inserta al inicio de la lista de colisiones para operación ágil en tiempo O(1)
            head = node; // Actualiza el puntero inicial apuntando al nuevo nodo insertado
            size++;
        }

        Session find(String token) {
            ChainNode cur = head;
            while (cur != null) {
                if (cur.session.token.equals(token)) return cur.session; // Devuelve referencia si coinciden las cadenas
                cur = cur.next;
            }
            return null;
        }

        boolean remove(String token) {
            if (head == null) return false;
            if (head.session.token.equals(token)) { head = head.next; size--; return true; }
            ChainNode cur = head;
            while (cur.next != null) {
                if (cur.next.session.token.equals(token)) {
                    cur.next = cur.next.next; // Cortocircuita el nodo a remover reasignando puntero siguiente
                    size--;
                    return true;
                }
                cur = cur.next;
            }
            return false;
        }

        int removeExpired() {
            int removed = 0;
            while (head != null && head.session.isExpired()) { // Limpieza en cascada si las cabezas encadenadas expiraron
                head = head.next; // Purga el nodo inicial desplazando la raíz
                size--;
                removed++; // Registra la baja lógica del nodo caducado
            }
            if (head == null) return removed;
            ChainNode cur = head;
            while (cur.next != null) {
                if (cur.next.session.isExpired()) { // Evalúa de forma prospectiva la caducidad temporal
                    cur.next = cur.next.next; // Remueve físicamente enlazando el nodo actual con el "nieto"
                    size--;
                    removed++;
                } else {
                    cur = cur.next; // Solo avanza si el subnodo continuo sigue vigente
                }
            }
            return removed; // Retorna cantidad de bajas consolidadas en este tramo
        }
    }

    static class SessionCache {
        private Chain[] table;
        private int     tableSize;
        private int     totalSessions = 0;

        SessionCache(int size) {
            this.tableSize = nextPrime(size);
            this.table = new Chain[tableSize];
            for (int i = 0; i < tableSize; i++) table[i] = new Chain();
            System.out.println("SessionCache iniciado. Tamaño tabla: " + tableSize);
        }

        private int hash(String token) {
            int h = token.hashCode() % tableSize; // Computa el valor hash primitivo intrínseco de Java
            return (h < 0) ? h + tableSize : h; // Técnica de corrección matemática para neutralizar residuos negativos de desbordamiento
        }

        public void login(String token, String username, String role, long ttlMs) {
            int idx = hash(token);
            Session existing = table[idx].find(token);
            if (existing != null) {
                System.out.println("  Token ya existe. Actualizando sesión de " + username);
                table[idx].remove(token); // Purga sesión preexistente homónima para prevenir colisión semántica
                totalSessions--;
            }
            long expiresAt = System.currentTimeMillis() + ttlMs; // Determina el umbral de muerte temporal absoluto
            Session s = new Session(token, username, role, expiresAt);
            table[idx].add(s); // Inserta el objeto sesión en la cubeta hash determinada
            totalSessions++;
            System.out.println("  LOGIN: " + s + "  → tabla[" + idx + "]");
        }

        public Session validate(String token) {
            int idx = hash(token);
            Session s = table[idx].find(token); // Ejecuta búsqueda secuencial acotada al contenedor hash
            if (s == null) {
                System.out.println("  VALIDATE [" + token + "]: no encontrado.");
                return null;
            }
            if (s.isExpired()) { // Filtro protector: aunque el registro exista físicamente, se rechaza si caducó cronológicamente
                System.out.println("  VALIDATE [" + token + "]: EXPIRADO. Usuario: " + s.username);
                return null;
            }
            System.out.println("  VALIDATE [" + token + "]: VÁLIDO. Usuario: " + s.username + " | Rol: " + s.role);
            return s; // Retorna credencial activa autorizada
        }

        public void logout(String token) {
            int idx = hash(token);
            boolean removed = table[idx].remove(token); // Efectúa baja explícita a petición del usuario
            if (removed) {
                totalSessions--;
                System.out.println("  LOGOUT [" + token + "]: sesión cerrada.");
            } else {
                System.out.println("  LOGOUT [" + token + "]: token no encontrado.");
            }
        }

        public void cleanExpired() {
            int totalRemoved = 0;
            for (int i = 0; i < tableSize; i++) { // Escanea exhaustivamente todas las posiciones del arreglo de chains
                int removed = table[i].removeExpired(); // Delega a la sublista la depuración interna de elementos caducados
                totalSessions -= removed; // Decrementa métrica global de control activo
                totalRemoved  += removed;
            }
            System.out.println("  cleanExpired(): " + totalRemoved + " sesión(es) eliminada(s). Sesiones activas: " + totalSessions);
        }

        public void printStatus() { System.out.println("  Sesiones activas en caché: " + totalSessions); }
    }

    public static void main(String[] args) throws InterruptedException {
        // Pruebas omitidas para concisión
    }

    static int nextPrime(int n) {
        if (n <= 2) return 2;
        int c = (n % 2 == 0) ? n + 1 : n;
        while (!isPrime(c)) c += 2;
        return c;
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) if (n % i == 0) return false;
        return true;
    }
}