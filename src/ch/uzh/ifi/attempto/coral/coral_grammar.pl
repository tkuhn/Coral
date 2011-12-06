% This file is part of Coral.
% Copyright 2011, Tobias Kuhn and Stefan Hoefler.
% 
% Coral is free software: you can redistribute it and/or modify it under the terms of the GNU
% Lesser General Public License as published by the Free Software Foundation, either version 3 of
% the License, or (at your option) any later version.
% 
% Coral is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
% the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
% General Public License for more details.
% 
% You should have received a copy of the GNU Lesser General Public License along with Coral. If
% not, see http://www.gnu.org/licenses/.


{ sem:lam(C, C) }:
query ~>
    conditions.

{ sem:lam(C, C) }:
conditions =>
    condition.

{ sem:lam(C, lam(T, C + ' & ' + T )) }:
conditions =>
    condition,
    conditions.

{ sem:lam(NP, NP) }:
condition =>
    ['there is'],
    n,
    $sentence_delimiter.

{ sem:lam(NP, lam(VP, NP + VP )) }:
condition =>
    np(id:ID, type:T),
    vp_coord(id:ID, domain:T),
    $sentence_delimiter.

{ sem:lam(N, N + ' & ' ) }:
np(id:ID, type:T, rel:Rel, def:minus) =>
    n(id:ID, type:T, rel:Rel).

{ sem:'' }:
np(id:ID, type:T, rel:minus, def:plus) =>
    $ref(noun:N, string:St, num:Num, id:ID, type:T),
    <(noun:N, string:St, num:Num, id:ID).

{ sem:lam(N, lam(V, lam(P, lam(R, N + V + P + R )))) }:
n(id:ID, type:T, rel:Rel) =>
    el(id:ID, string:S, type:T),
    opt_bare_value(id:ID, string:S, type:T),
    opt_properties(id:ID, domain:T),
    rel(id:ID, domain:T, rel:Rel).

{ sem:'@' + ID + ' ' + Sem }:
el(id:ID, type:T, noun:N, string:'', num:Num) =>
    # ID,
    $article_noun(noun:N, id:ID, sem:Sem, type:T),
    opt_num(num:Num),
    >(noun:N, string:'', num:Num, id:ID).

{ sem:lam(T, '@' + ID1 + ' ' + Sem + ' & ' + T + ' & #' + ID1 + ' _=_ #' + ID2 ) }:
el(id:ID1, type:leaf, noun:N, string:St, num:Num) =>
    # ID1,
    $article_noun(noun:N, id:ID1, sem:Sem, type:leaf),
    token(text:St, id:ID2),
    opt_num(num:Num),
    >(noun:N, string:St, num:Num, id:ID1).

{ sem:lam(T, T) }:
el(id:ID, type:leaf, noun:'', string:St, num:Num) =>
    token(text:St, id:ID),
    opt_num(num:Num),
    >(noun:token, string:St, num:minus, id:ID).

{ sem:'@' + ID + ' "' + T + '"' }:
token(text:T, id:ID) =>
    # ID,
    $string(text:T, type:token).

{ sem:'' }:
opt_properties => [].

{ sem:lam(P, P) }:
opt_properties(id:Subj, domain:D) =>
    [with],
    properties(id:Subj, domain:D).

{ sem:lam(P, ' & ' + P ) }:
properties(id:Subj, domain:D) =>
    property(id:Subj, domain:D).

{ sem:lam(P, lam(Q, ' & ' + P + Q )) }:
properties(id:Subj, domain:D) =>
    property(id:Subj, domain:D),
    ['and with'],
    properties(id:Subj, domain:D).

{ sem:'#' + ID + ':arity=' + I }:
property(id:ID, domain:node) =>
    [arity],
    $integer(text:I).

{ sem:'#' + ID + ':tokenarity=' + I }:
property(id:ID, domain:node) =>
    [length],
    $integer(text:I).

{ sem:'@' + ID + ' tok="' + V + '" & #' + Subj + ' _=_ #' + ID }:
property(id:Subj, domain:leaf) =>
    # ID,
    ['the text'],
    $string(text:V, type:propval).

{ sem:lam(V, '@' + ID2 + ' ' + V + ' & #' + ID1 + ' _=_ #' + ID2 ) }:
property(id:ID1, domain:leaf) =>
    ['a text'],
    # ID2,
    value(prop:tok, type:propval).

{ sem:'@' + ID + ' ' + P + '="' + V + '" & #' + Subj + ' _=_ #' + ID }:
property(id:Subj, domain:D) =>
    # ID,
    $property(sem:P, domain:D, general:minus),
    $string(text:V, type:propval).

{ sem:lam(V, '@' + ID2 + ' ' + V + ' & #' + ID1 + ' _=_ #' + ID2 ) }:
property(id:ID1, domain:D) =>
    $property(sem:P, domain:D, general:plus),
    # ID2,
    value(prop:P, type:propval).

{ sem:lam(V, '@' + ID2 + ' ' + V + ' & #' + ID1 + ' _=_ #' + ID2 ) }:
property(id:ID1) =>
    ['an attribute'],
    $string(text:P, type:propname),
    # ID2,
    value(prop:P, type:propval).

{ sem:'' }:
opt_bare_value => [].

{ sem:lam(V, ' & @' + ID2 + ' ' + V + ' & #' + ID1 + ' _=_ #' + ID2 ) }:
opt_bare_value(id:ID1, string:'', type:leaf) =>
    # ID2,
    value(prop:tok, type:token).

{ sem:P + '="' + V + '"' }:
value(prop:P, type:T) =>
    ['of value'],
    $string(text:V, type:T).

{ sem:P + '="' + V + '"' }:
value(prop:P, type:T) =>
    ['that', 'has the value'],
    $string(text:V, type:T).

{ sem:P + '!="' + V + '"' }:
value(prop:P, type:T) =>
    ['that', 'does not have the value'],
    $string(text:V, type:T).

{ sem:P + '=/' + V + '/' }:
value(prop:P, type:T) =>
    ['that', 'matches'],
    $string(text:V, type:T).

{ sem:P + '!=/' + V + '/' }:
value(prop:P, type:T) =>
    ['that', 'does not match'],
    $string(text:V, type:T).

{ sem:'' }:
rel(rel:minus) => [].

{ sem:lam(VP, ' & ' + VP ) }:
rel(id:ID, domain:T, rel:plus) =>
    [that],
    vp(id:ID, domain:T).

{ sem:lam(VP, VP) }:
vp_coord(id:ID, domain:D) =>
    vp(id:ID, domain:D).

{ sem:lam(VP, lam(C, VP + ' & ' + C )) }:
vp_coord(id:ID, domain:D) =>
    vp(id:ID, domain:D),
    [and],
    vp_coord(id:ID, domain:D).

{ sem:lam(P, P) }:
vp(id:Subj, domain:D) =>
    [has],
    property(id:Subj, domain:D).

{ sem:lam(NP, lam(M, NP + ' #' + Subj + ' ' + Sem + M + ' #' + Obj )) }:
vp(id:Subj, domain:D) =>
    $v(type:T, dir:forward, sem:Sem, domain:D, range:R),
    np(id:Obj, type:R, rel:Rel),
    modifier(type:T, rel:Rel).

{ sem:lam(NP, lam(M, NP + ' #' + Obj + ' ' + Sem + M + ' #' + Subj )) }:
vp(id:Subj, domain:D) =>
    $v(type:T, dir:backward, sem:Sem, domain:D, range:R),
    np(id:Obj, type:R, rel:Rel),
    modifier(type:T, rel:Rel).

{ sem:'' }:
modifier(type:normal) => [].

{ sem:Sem }:
modifier(type:hasrole, rel:minus) =>
    [as],
    $role(sem:Sem).

{ sem:I + ',' + I }:
modifier(type:hasdist, rel:minus) =>
    ['at a distance of'],
    $integer(text:I),
    [tokens].

{ sem:I1 + ',' + I2 }:
modifier(type:hasdist, rel:minus) =>
    ['at a distance of'],
    $integer(text:I1),
    [to],
    $integer(text:I2),
    [tokens].

{ sem:I + ',' + I }:
modifier(type:hasdepth, rel:minus) =>
    ['at a depth of'],
    $integer(text:I),
    [structures].

{ sem:I1 + ',' + I2 }:
modifier(type:hasdepth, rel:minus) =>
    ['at a depth of'],
    $integer(text:I1),
    [to],
    $integer(text:I2),
    [structures].

{ sem:T }:
modifier(type:hasprop, rel:minus) =>
    [via],
    ['the relation type'],
    $string(text:T, type:relationtype).

{ sem:'[' + P + '="' + V + '"' + ']' }:
modifier(type:hasprop, rel:minus) =>
    [via],
    $property(sem:P, domain:relation, general:minus),
    $string(text:V, type:propval).

{ sem:lam(V, '[' + V + ']' ) }:
modifier(type:hasprop, rel:minus) =>
    [via],
    $property(sem:P, domain:relation, general:plus),
    value(prop:P, type:propval).

{ sem:lam(V, '[' + V + ']' ) }:
modifier(type:hasprop, rel:minus) =>
    [via],
    ['an attribute'],
    $string(text:P, type:propname),
    value(prop:P, type:propval).

opt_num(num:minus) => [].

opt_num(num:Num) =>
    $num(num:Num),
    /<(num:Num).

$sentence_delimiter =>
    [';'].

$sentence_delimiter =>
    [and].
