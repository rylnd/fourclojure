(ns fourclojure.core)

(defn prob32 [answer]
  (and
    (= (answer [1 2 3]) '(1 1 2 2 3 3))
    (= (answer [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
    (= (answer [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
    (= (answer [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))))

(def sol32
  #(interleave % %))

(defn prob156 [answer]
  (and
    (= (answer 0 [:a :b :c]) {:a 0 :b 0 :c 0})
    (= (answer "x" [1 2 3]) {1 "x" 2 "x" 3 "x"})
    (= (answer [:a :b] [:foo :bar]) {:foo [:a :b] :bar [:a :b]})))

(def sol156
  #(into {} (map vector %2 (repeat %))))

(defn prob34 [answer]
  (and
    (= (answer 1 4) '(1 2 3))
    (= (answer -2 2) '(-2 -1 0 1))
    (= (answer 5 8) '(5 6 7))))

(def sol34
  #(take (- %2 %)
    (iterate inc %)))

(defn prob42 [answer]
  (and
    (= (answer 1) 1)
    (= (answer 3) 6)
    (= (answer 5) 120)
    (= (answer 8) 40320)))

(def sol42
  #(apply * (range 1 (inc %))))

(defn prob33 [answer]
  (and
    (= (answer [1 2 3] 2) '(1 1 2 2 3 3))
    (= (answer [:a :b] 4) '(:a :a :a :a :b :b :b :b))
    (= (answer [4 5 6] 1) '(4 5 6))
    (= (answer [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))
    (= (answer [44 33] 2) [44 44 33 33])))

(def sol33
  (fn [xs n]
    (reduce concat (map (partial repeat n) xs))))

(defn prob27 [answer]
  (and
    (false? (answer '(1 2 3 4 5)))
    (true? (answer "racecar"))
    (true? (answer [:foo :bar :foo]))
    (true? (answer '(1 1 3 3 1 1)))
    (false? (answer '(:a :b :c)))))

(def sol27
  #(= (seq %) (reverse %)))

(defn prob40 [answer]
  (and
    (= (answer 0 [1 2 3]) [1 0 2 0 3])
    (= (apply str (answer ", " ["one" "two" "three"])) "one, two, three")
    (= (answer :z [:a :b :c :d]) [:a :z :b :z :c :z :d])))

(def sol40
  #(rest (interleave (repeat %) %2)))

(defn prob31 [answer]
  (and
    (= (answer [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
    (= (answer [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
    (= (answer [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))))

(def sol31
  #(partition-by identity %))

(defn prob41 [answer]
  (and
    (= (answer [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])
    (= (answer [:a :b :c :d :e :f] 2) [:a :c :e])
    (= (answer [1 2 3 4 5 6] 4) [1 2 3 5 6])))

; whoops, this is really drop-nth, not drop-every-nth
; (def sol41
;   (fn [coll n]
;     (keep-indexed #(when (not= % (dec n)) %2) coll)))

(def sol41
  #(mapcat drop-last
           (partition %2 %2 [nil] %)))

(def is-4clojure #(re-find #"(prob|sol)(\d+)" (str %)))

(def by-num
  (group-by #(re-find #"\d+" (str %))
            (sort-by str
                     (filter is-4clojure
                             (vals (ns-publics 'fourclojure.core))))))
(every? true?
        (for [[k v] by-num
              :let [[t s] v]]
          (t s)))
