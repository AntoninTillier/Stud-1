**Logic : PROLOG project Doukipudonktan!**


1. Introduction 

The present project aims to have you develop a Prolog program that involves a recursive data structure we have not covered in class but that you will encounter in advanced algorithmics courses: lists. Therefore, you will need to apply the skills you have acquired in recent weeks in terms of logic programming and recursive programming. You will also need to independently seek new knowledge to understand list programming in Prolog.

2. A recursive data structure: lists

The objective of this project is to have you develop a real Prolog program that involves a recursive data structure we have not covered in class but will be central to your advanced algorithmics course in the second semester: lists. Therefore, you will need to apply the skills you have acquired in recent weeks in terms of recursive programming. You will also need to independently seek new knowledge to understand list programming in Prolog.

Lists are a very general data structure that can represent, for example, sets whose cardinality (the number of elements) is not known in advance and can change over time. To enable this, this data structure is defined in a recursive manner, following the following rules:

- The atom [] is an empty list, which represents an empty set (this is the base case).
- Any list L can be decomposed into a head T, representing the first element of the list, and a list Q, called the tail of the list. In Prolog, we denote L in the form [T | Q].

It is evident that the definition is recursive since the concept of a list is defined based on the list itself (for the tail). The tail of the list, having one less element (T) than the list itself, leads to a list where the tail is an empty list by recursion: this is the base case.

These structures are useful, for example, for representing linguistic data. Take a given sentence; we may not know its length, but it is composed of a set of words, and each word becomes an element in the list. This recursive structure allows modeling sentences of varying lengths.

3. Topic: One Hundred Trillion Poems


Raymond Queneau, the author of "Zazie in the Metro," whose famous opening sentence ("Doukipudonktan!") is well-known, was the Grand Satrap of the College of Pataphysics and is etched in literary history as the co-founder of Oulipo (Workshop of Potential Literature). This literary research movement places significant emphasis on formal language games and draws heavy influence from the connections that can exist between mathematics, language, and literature. Queneau's work, "One Hundred Trillion Poems," published in 1961 and the focus of this project, is representative of this literary movement.

Indeed, 'One Hundred Trillion Poems' is a book-object (available at the University Library of Tours) that allows the reader to independently combine verses invented by Queneau in order to compose 10^14 different poems in the form of sonnets. As Queneau himself points out, 'It's, after all, a kind of machine for making poems, but in a limited number; it is true that this limited number, although finite, provides reading material for nearly two hundred million years (reading twenty-four hours a day).' Thus, no one has ever read all the poems contained in this work. Queneau mentioned a machine? Well then, a computer program, specifically a Prolog program, should be able to generate all these poems on its own. This is the purpose of this project, for which we aim to:

- Blind Generation - Initially, create a program that generates absolutely all the poems imagined by Queneau in his book-object
- Directed Generation - In a second phase, create a program that generates the poem envisioned by Queneau by providing as input the specific 'page' that one wants to reach

Understanding the Problem — To solve the problem, it's essential to first comprehend how the creation of poems works in Queneau's book-object, as this is the process we aim to replicate. Several sources can assist you in discovering this work:

- https://fr.wikipedia.org/wiki/Cent\_mille\_milliards\_de\_po%C3%A8mes
- http://mathweb.free.fr/quotidien/queneau.php3

Knowledge Representation — We aim to create two Prolog programs that, starting from the 14 strips of 10 lines that make up 'One Hundred Trillion Poems,' will be capable of reproducing one or all imaginable poems. To represent the 140 (14 * 10) lines written by Queneau, we will use list structures.

Each line can be represented in the form of a list of words. As for the set of possibilities for this first line, it can be represented as a list of lists. In the context of this project, we will limit ourselves to 4 possibilities for constructing each line, based on 4 poems from the work, which I provide in the appendix of the subject.

1. To represent this knowledge, you will use one or more carefully chosen predicates to describe, in the form of facts, these basic phrases that are part of the construction of the poems
2. Blind Poem Generation - Here, you are asked to create a predicate 'tout_poeme(Poeme)' that succeeds if 'Poem' is a list of lists of words representing any poem generated from the book.
3. Formatted Poem Generation - The display allowed by 'tout_poeme(X)' is not very user-friendly. Create a 'tout_poeme_formatte' predicate with arity 0 that succeeds in displaying a poem on the screen
4. Directed Poem Generation - The previous program allows, through backtracking, the creation of all possible poems. However, now we would like to create a program corresponding to a specific 'page.' For example, the page [1,1,1,1,1,1,1,2,2,2,2,2,2,2] corresponds to a poem where the first possibility is taken for the first 7 lines, and the second for the last 7. To do this, create a 'poeme_page(Page, Poem)' predicate that succeeds if 'Poem' is the poem corresponding to the 'Page,' represented in the form of a list

All predicates defined in this program must be systematically tested, and a file will provide a comprehensive account of the tests performed.