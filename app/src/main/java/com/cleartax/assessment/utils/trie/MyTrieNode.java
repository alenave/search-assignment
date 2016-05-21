package com.cleartax.assessment.utils.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alenave on 20/05/16.
 */
class MyTrieNode {
    char data;
    boolean is_end_of_string;
    Map<Character, MyTrieNode> nodes;
    int frequency = 0;
    int minHeapIndex = -1;

    public MyTrieNode(char data) {
        this.data = data;
        is_end_of_string = false;
        nodes = new HashMap<Character, MyTrieNode>();
    }

    public MyTrieNode children(char data) {
        return nodes.get(data);
    }

    public boolean isChildExist(char c) {
        return children(c) != null;
    }
}
