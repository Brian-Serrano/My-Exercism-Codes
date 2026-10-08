<?php

function language_list(...$languages)
{
    return $languages;
}

function add_to_language_list($language_list, $language)
{
    $language_list[] = $language;
    return $language_list;
}

function prune_language_list($language_list)
{
    array_shift($language_list);
    return $language_list;
}

function current_language($language_list)
{
    return reset($language_list);
}

function language_list_length($language_list)
{
    return count($language_list);
}