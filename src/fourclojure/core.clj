(ns fourclojure.core)

(defn prob32 [answer]
  (and
    (= (answer [1 2 3]) '(1 1 2 2 3 3))
    (= (answer [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
    (= (answer [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
    (= (answer [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))))

(def sol32
  #(interleave % %))

(true? (prob32 sol32))

(defn prob156 [answer]
  (and
    (= (answer 0 [:a :b :c]) {:a 0 :b 0 :c 0})
    (= (answer "x" [1 2 3]) {1 "x" 2 "x" 3 "x"})
    (= (answer [:a :b] [:foo :bar]) {:foo [:a :b] :bar [:a :b]})))

(def sol156
  #(into {} (map vector %2 (repeat %))))

(true? (prob156 sol156))


(defn prob34 [answer]
  (and
    (= (answer 1 4) '(1 2 3))
    (= (answer -2 2) '(-2 -1 0 1))
    (= (answer 5 8) '(5 6 7))))

(def sol34
  #(take (- %2 %)
    (iterate inc %)))

(true? (prob34 sol34))

(defn prob42 [answer]
  (and
    (= (answer 1) 1)
    (= (answer 3) 6)
    (= (answer 5) 120)
    (= (answer 8) 40320)))

(def sol42
  #(apply * (range 1 (inc %))))

(true? (prob42 sol42))
