<?php

/*
 * By adding type hints and enabling strict type checking, code can become
 * easier to read, self-documenting and reduce the number of potential bugs.
 * By default, type declarations are non-strict, which means they will attempt
 * to change the original type to match the type specified by the
 * type-declaration.
 *
 * In other words, if you pass a string to a function requiring a float,
 * it will attempt to convert the string value to a float.
 *
 * To enable strict mode, a single declare directive must be placed at the top
 * of the file.
 * This means that the strictness of typing is configured on a per-file basis.
 * This directive not only affects the type declarations of parameters, but also
 * a function's return type.
 *
 * For more info review the Concept on strict type checking in the PHP track
 * <link>.
 *
 * To disable strict typing, comment out the directive below.
 */

declare(strict_types=1);

class BinarySearchTree
{
    public ?BinarySearchTree $left;
    public ?BinarySearchTree $right;
    public int $data;

    public function __construct(int $data)
    {
        $this->left = null;
        $this->right = null;
        $this->data = $data;
    }

    public function insert(int $data)
    {
        $this->insrt($data, $this);
    }

    private function insrt(int $data, ?BinarySearchTree $node)
    {
        if ($node->data >= $data) {
            if ($node->left != null) {
                $this->insrt($data, $node->left);
            }
            else {
                $node->left = new BinarySearchTree($data);
            }
        }
        else {
            if ($node->right != null) {
                $this->insrt($data, $node->right);
            }
            else {
                $node->right = new BinarySearchTree($data);
            }
        }
    }

    public function getSortedData(): array
    {
        $nodeData = [];
        $this->inOrderTraversal($this, $nodeData);
        return $nodeData;
    }

    private function inOrderTraversal(?BinarySearchTree $node, array &$nodeData)
    {
        if ($node != null) {
            $this->inOrderTraversal($node->left, $nodeData);
            $nodeData[] = $node->data;
            $this->inOrderTraversal($node->right, $nodeData);
        }
    }
}
