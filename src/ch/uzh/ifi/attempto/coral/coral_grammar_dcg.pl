% This code is automatically generated on the basis of a file in Codeco notation.
%
% For more information, see the package ch.uzh.ifi.attempto.codeco of the AceWiki system
% (http://attempto.ifi.uzh.ch/acewiki/) and the thesis "Controlled English for Knowledge
% Representation" (http://attempto.ifi.uzh.ch/site/pubs/papers/doctoral_thesis_kuhn.pdf).

query([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, O)}:query, P), Q/R)-->conditions([S, T, U, V, W, X, Y, Z, A1, B1, C1, D1, E1, F1], P, Q/G1), ~(Q/G1/R).
conditions([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, O)}:conditions, P), Q/R)-->condition([S, T, U, V, W, X, Y, Z, A1, B1, C1, D1, E1, F1], P, Q/R).
conditions([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, lam(P, O+' & '+P))}:conditions, (Q, R)), S/T)-->condition([U, V, W, X, Y, Z, A1, B1, C1, D1, E1, F1, G1, H1], Q, S/I1), conditions([J1, K1, L1, M1, N1, O1, P1, Q1, R1, S1, T1, U1, V1, W1], R, I1/T).
condition([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, O)}:condition, (['there is'], P, Q)), R/S)-->['there is'], n([T, U, V, W, X, Y, Z, A1, B1, C1, D1, E1, F1, G1], P, R/S), $sentence_delimiter([H1, I1, J1, K1, L1, M1, N1, O1, P1, Q1, R1, S1, T1, U1], Q, S/S).
condition([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, lam(P, O+P))}:condition, (Q, R, S)), T/U)-->np([V, W, X, Y, Z, A1, B1, C1, D1, E1, F1, G1, H1, I1], Q, T/J1), vp_coord([V, K1, W, L1, M1, N1, O1, P1, Q1, R1, S1, T1, U1, V1], R, J1/U), $sentence_delimiter([W1, X1, Y1, Z1, A2, B2, C2, D2, E2, F2, G2, H2, I2, J2], S, U/U).
np([A, B, C, D, minus, E, F, G, H, I, J, K, L, M], =>({sem:lam(N, N+' & ')}:np(id:A, type:B, rel:D, def:minus), O), P/Q)-->n([A, B, R, D, S, T, U, V, W, X, Y, Z, A1, B1], O, P/Q).
np([A, B, C, minus, plus, D, E, F, G, H, I, J, K, L], =>({sem:''}:np(id:A, type:B, rel:minus, def:plus), (M, <(noun:N, string:O, num:P, id:A))), Q/R)--> $ref([A, B, S, T, U, N, O, P, V, W, X, Y, Z, A1], M, Q/Q), [+[A, B1, C1, D1, E1, N, O, P, F1, G1, H1, I1, J1, K1]]<Q/R.
n([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, lam(P, lam(Q, lam(R, O+P+Q+R))))}:n(id:A, type:B, rel:D), (S, T, U, V)), W/X)-->el([A, B, Y, Z, A1, B1, C1, D1, E1, F1, G1, H1, I1, J1], S, W/K1), opt_bare_value([A, B, L1, M1, N1, O1, C1, P1, Q1, R1, S1, T1, U1, V1], T, K1/W1), opt_properties([A, X1, B, Y1, Z1, A2, B2, C2, D2, E2, F2, G2, H2, I2], U, W1/J2), rel([A, K2, B, D, L2, M2, N2, O2, P2, Q2, R2, S2, T2, U2], V, J2/X).
el([A, B, C, D, E, F, '', G, H, I, J, K, L, M], =>({sem: @ + A+' '+N}:el(id:A, type:B, noun:F, string:'', num:G), (#(A), O, P, >(noun:F, string:'', num:G, id:A))), Q/R)--> #(A), $article_noun([A, B, S, T, U, F, V, W, N, X, Y, Z, A1, B1], O, Q/Q), opt_num([C1, D1, E1, F1, G1, H1, I1, G, J1, K1, L1, M1, N1, O1], P, Q/P1), [A, Q1, R1, S1, T1, F, '', G, U1, V1, W1, X1, Y1, Z1]>P1/R.
el([A, leaf, B, C, D, E, F, G, H, I, J, K, L, M], =>({sem:lam(N, @ + A+' '+O+' & '+N+' & #'+A+' _=_ #'+P)}:el(id:A, type:leaf, noun:E, string:F, num:G), (#(A), Q, R, S, >(noun:E, string:F, num:G, id:A))), T/U)--> #(A), $article_noun([A, leaf, V, W, X, E, Y, Z, O, A1, B1, C1, D1, E1], Q, T/T), token([P, F1, G1, H1, I1, J1, K1, L1, M1, F, N1, O1, P1, Q1], R, T/R1), opt_num([S1, T1, U1, V1, W1, X1, Y1, G, Z1, A2, B2, C2, D2, E2], S, R1/F2), [A, G2, H2, I2, J2, E, F, G, K2, L2, M2, N2, O2, P2]>F2/U.
el([A, leaf, B, C, D, '', E, F, G, H, I, J, K, L], =>({sem:lam(M, M)}:el(id:A, type:leaf, noun:'', string:E, num:F), (N, O, >(noun:token, string:E, num:minus, id:A))), P/Q)-->token([A, R, S, T, U, V, W, X, Y, E, Z, A1, B1, C1], N, P/D1), opt_num([E1, F1, G1, H1, I1, J1, K1, F, L1, M1, N1, O1, P1, Q1], O, D1/R1), [A, S1, T1, U1, V1, token, E, minus, W1, X1, Y1, Z1, A2, B2]>R1/Q.
token([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem: @ + A+' "'+J+'"'}:token(text:J, id:A), (#(A), O)), P/P)--> #(A), $string([Q, token, R, S, T, U, V, W, X, J, Y, Z, A1, B1], O, P/P).
opt_properties([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:''}:opt_properties, []), O/O)-->[].
opt_properties([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, O)}:opt_properties(id:A, domain:C), ([with], P)), Q/R)-->[with], properties([A, S, C, T, U, V, W, X, Y, Z, A1, B1, C1, D1], P, Q/R).
properties([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, ' & '+O)}:properties(id:A, domain:C), P), Q/R)-->property([A, S, C, T, U, V, W, X, Y, Z, A1, B1, C1, D1], P, Q/R).
properties([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, lam(P, ' & '+O+P))}:properties(id:A, domain:C), (Q, ['and with'], R)), S/T)-->property([A, U, C, V, W, X, Y, Z, A1, B1, C1, D1, E1, F1], Q, S/G1), ['and with'], properties([A, H1, C, I1, J1, K1, L1, M1, N1, O1, P1, Q1, R1, S1], R, G1/T).
property([A, B, node, C, D, E, F, G, H, I, J, K, L, M], =>({sem: # + A+':arity='+N}:property(id:A, domain:node), ([arity], O)), P/P)-->[arity], $integer([Q, R, S, T, U, V, W, X, Y, N, Z, A1, B1, C1], O, P/P).
property([A, B, node, C, D, E, F, G, H, I, J, K, L, M], =>({sem: # + A+':tokenarity='+N}:property(id:A, domain:node), ([length], O)), P/P)-->[length], $integer([Q, R, S, T, U, V, W, X, Y, N, Z, A1, B1, C1], O, P/P).
property([A, B, leaf, C, D, E, F, G, H, I, J, K, L, M], =>({sem: @ + N+' tok="'+O+'" & #'+A+' _=_ #'+N}:property(id:A, domain:leaf), (#(N), ['the text'], P)), Q/Q)--> #(N), ['the text'], $string([R, propval, S, T, U, V, W, X, Y, O, Z, A1, B1, C1], P, Q/Q).
property([A, B, leaf, C, D, E, F, G, H, I, J, K, L, M], =>({sem:lam(N, @ + O+' '+N+' & #'+A+' _=_ #'+O)}:property(id:A, domain:leaf), (['a text'], #(O), P)), Q/R)-->['a text'], #(O), value([S, propval, T, U, V, W, X, Y, Z, A1, tok, B1, C1, D1], P, Q/R).
property([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem: @ + O+' '+P+'="'+Q+'" & #'+A+' _=_ #'+O}:property(id:A, domain:C), (#(O), R, S)), T/T)--> #(O), $property([U, V, C, W, X, Y, Z, A1, P, B1, C1, minus, D1, E1], R, T/T), $string([F1, propval, G1, H1, I1, J1, K1, L1, M1, Q, N1, O1, P1, Q1], S, T/T).
property([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, @ + P+' '+O+' & #'+A+' _=_ #'+P)}:property(id:A, domain:C), (Q, #(P), R)), S/T)--> $property([U, V, C, W, X, Y, Z, A1, B1, C1, D1, plus, E1, F1], Q, S/S), #(P), value([G1, propval, H1, I1, J1, K1, L1, M1, N1, O1, B1, P1, Q1, R1], R, S/T).
property([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, @ + P+' '+O+' & #'+A+' _=_ #'+P)}:property(id:A), (['an attribute'], Q, #(P), R)), S/T)-->['an attribute'], $string([U, propname, V, W, X, Y, Z, A1, B1, C1, D1, E1, F1, G1], Q, S/S), #(P), value([H1, propval, I1, J1, K1, L1, M1, N1, O1, P1, C1, Q1, R1, S1], R, S/T).
opt_bare_value([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:''}:opt_bare_value, []), O/O)-->[].
opt_bare_value([A, leaf, B, C, D, E, '', F, G, H, I, J, K, L], =>({sem:lam(M, ' & @'+N+' '+M+' & #'+A+' _=_ #'+N)}:opt_bare_value(id:A, string:'', type:leaf), (#(N), O)), P/Q)--> #(N), value([R, token, S, T, U, V, W, X, Y, Z, tok, A1, B1, C1], O, P/Q).
value([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:K+'="'+O+'"'}:value(prop:K, type:B), (['of value'], P)), Q/Q)-->['of value'], $string([R, B, S, T, U, V, W, X, Y, O, Z, A1, B1, C1], P, Q/Q).
value([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:K+'="'+O+'"'}:value(prop:K, type:B), ([that, 'has the value'], P)), Q/Q)-->[that, 'has the value'], $string([R, B, S, T, U, V, W, X, Y, O, Z, A1, B1, C1], P, Q/Q).
value([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:K+'!="'+O+'"'}:value(prop:K, type:B), ([that, 'has not the value'], P)), Q/Q)-->[that, 'has not the value'], $string([R, B, S, T, U, V, W, X, Y, O, Z, A1, B1, C1], P, Q/Q).
value([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:K+ =/ + O+ /}:value(prop:K, type:B), ([that, matches], P)), Q/Q)-->[that, matches], $string([R, B, S, T, U, V, W, X, Y, O, Z, A1, B1, C1], P, Q/Q).
value([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:K+'!=/'+O+ /}:value(prop:K, type:B), ([that, 'does not match'], P)), Q/Q)-->[that, 'does not match'], $string([R, B, S, T, U, V, W, X, Y, O, Z, A1, B1, C1], P, Q/Q).
rel([A, B, C, minus, D, E, F, G, H, I, J, K, L, M], =>({sem:''}:rel(rel:minus), []), N/N)-->[].
rel([A, B, C, plus, D, E, F, G, H, I, J, K, L, M], =>({sem:lam(N, ' & '+N)}:rel(id:A, domain:C, rel:plus), ([that], O)), P/Q)-->[that], vp([A, R, C, S, T, U, V, W, X, Y, Z, A1, B1, C1], O, P/Q).
vp_coord([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, O)}:vp_coord(id:A, domain:C), P), Q/R)-->vp([A, S, C, T, U, V, W, X, Y, Z, A1, B1, C1, D1], P, Q/R).
vp_coord([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, lam(P, O+' & '+P))}:vp_coord(id:A, domain:C), (Q, [and], R)), S/T)-->vp([A, U, C, V, W, X, Y, Z, A1, B1, C1, D1, E1, F1], Q, S/G1), [and], vp_coord([A, H1, C, I1, J1, K1, L1, M1, N1, O1, P1, Q1, R1, S1], R, G1/T).
vp([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, O)}:vp(id:A, domain:C), ([has], P)), Q/R)-->[has], property([A, S, C, T, U, V, W, X, Y, Z, A1, B1, C1, D1], P, Q/R).
vp([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, lam(P, O+' #'+A+' '+Q+P+' #'+R))}:vp(id:A, domain:C), (S, T, U)), V/W)--> $v([X, Y, C, Z, A1, B1, C1, D1, Q, E1, F1, G1, forward, H1], S, V/V), np([R, H1, I1, J1, K1, L1, M1, N1, O1, P1, Q1, R1, S1, T1], T, V/U1), modifier([V1, Y, W1, J1, X1, Y1, Z1, A2, B2, C2, D2, E2, F2, G2], U, U1/W).
vp([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>({sem:lam(O, lam(P, O+' #'+Q+' '+R+P+' #'+A))}:vp(id:A, domain:C), (S, T, U)), V/W)--> $v([X, Y, C, Z, A1, B1, C1, D1, R, E1, F1, G1, backward, H1], S, V/V), np([Q, H1, I1, J1, K1, L1, M1, N1, O1, P1, Q1, R1, S1, T1], T, V/U1), modifier([V1, Y, W1, J1, X1, Y1, Z1, A2, B2, C2, D2, E2, F2, G2], U, U1/W).
modifier([A, normal, B, C, D, E, F, G, H, I, J, K, L, M], =>({sem:''}:modifier(type:normal), []), N/N)-->[].
modifier([A, hasrole, B, minus, C, D, E, F, G, H, I, J, K, L], =>({sem:M}:modifier(type:hasrole, rel:minus), ([as], N)), O/O)-->[as], $role([P, Q, R, S, T, U, V, W, M, X, Y, Z, A1, B1], N, O/O).
modifier([A, hasdist, B, minus, C, D, E, F, G, H, I, J, K, L], =>({sem:M+ (',')+M}:modifier(type:hasdist, rel:minus), (['at a distance of'], N, [tokens])), O/O)-->['at a distance of'], $integer([P, Q, R, S, T, U, V, W, X, M, Y, Z, A1, B1], N, O/O), [tokens].
modifier([A, hasdist, B, minus, C, D, E, F, G, H, I, J, K, L], =>({sem:M+ (',')+N}:modifier(type:hasdist, rel:minus), (['at a distance of'], O, [to], P, [tokens])), Q/Q)-->['at a distance of'], $integer([R, S, T, U, V, W, X, Y, Z, M, A1, B1, C1, D1], O, Q/Q), [to], $integer([E1, F1, G1, H1, I1, J1, K1, L1, M1, N, N1, O1, P1, Q1], P, Q/Q), [tokens].
modifier([A, hasdepth, B, minus, C, D, E, F, G, H, I, J, K, L], =>({sem:M+ (',')+M}:modifier(type:hasdepth, rel:minus), (['at a depth of'], N, [structures])), O/O)-->['at a depth of'], $integer([P, Q, R, S, T, U, V, W, X, M, Y, Z, A1, B1], N, O/O), [structures].
modifier([A, hasdepth, B, minus, C, D, E, F, G, H, I, J, K, L], =>({sem:M+ (',')+N}:modifier(type:hasdepth, rel:minus), (['at a depth of'], O, [to], P, [structures])), Q/Q)-->['at a depth of'], $integer([R, S, T, U, V, W, X, Y, Z, M, A1, B1, C1, D1], O, Q/Q), [to], $integer([E1, F1, G1, H1, I1, J1, K1, L1, M1, N, N1, O1, P1, Q1], P, Q/Q), [structures].
modifier([A, hasprop, B, minus, C, D, E, F, G, H, I, J, K, L], =>({sem:M}:modifier(type:hasprop, rel:minus), ([via], ['the relation type'], N)), O/O)-->[via], ['the relation type'], $string([P, relationtype, Q, R, S, T, U, V, W, M, X, Y, Z, A1], N, O/O).
modifier([A, hasprop, B, minus, C, D, E, F, G, H, I, J, K, L], =>({sem:'['+M+'="'+N+'"'+']'}:modifier(type:hasprop, rel:minus), ([via], O, P)), Q/Q)-->[via], $property([R, S, relation, T, U, V, W, X, M, Y, Z, minus, A1, B1], O, Q/Q), $string([C1, propval, D1, E1, F1, G1, H1, I1, J1, N, K1, L1, M1, N1], P, Q/Q).
modifier([A, hasprop, B, minus, C, D, E, F, G, H, I, J, K, L], =>({sem:lam(M, '['+M+']')}:modifier(type:hasprop, rel:minus), ([via], N, O)), P/Q)-->[via], $property([R, S, relation, T, U, V, W, X, Y, Z, A1, plus, B1, C1], N, P/P), value([D1, propval, E1, F1, G1, H1, I1, J1, K1, L1, Y, M1, N1, O1], O, P/Q).
modifier([A, hasprop, B, minus, C, D, E, F, G, H, I, J, K, L], =>({sem:lam(M, '['+M+']')}:modifier(type:hasprop, rel:minus), ([via], ['an attribute'], N, O)), P/Q)-->[via], ['an attribute'], $string([R, propname, S, T, U, V, W, X, Y, Z, A1, B1, C1, D1], N, P/P), value([E1, propval, F1, G1, H1, I1, J1, K1, L1, M1, Z, N1, O1, P1], O, P/Q).
opt_num([A, B, C, D, E, F, G, minus, H, I, J, K, L, M], =>(opt_num(num:minus), []), N/N)-->[].
opt_num([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>(opt_num(num:H), (O, /<(num:H))), P/Q)--> $num([R, S, T, U, V, W, X, H, Y, Z, A1, B1, C1, D1], O, P/P), /<([E1, F1, G1, H1, I1, J1, K1, H, L1, M1, N1, O1, P1, Q1], P/Q).
$sentence_delimiter([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>($sentence_delimiter, [;]), O/O)-->[;].
$sentence_delimiter([A, B, C, D, E, F, G, H, I, J, K, L, M, N], =>($sentence_delimiter, [and]), O/O)-->[and].


~(I/T/O) --> {append([X,[//|N],I],T), \+ member(//,N), findall(>>(R),member(>>(R),X),Y), append([Y,N,I],O)}, !.
~(_/O/O) --> [].
//(_, T/[//|T]) --> [].
>(F, T/[>(F)|T]) --> [].
>>(F, T/[>>(F)|T]) --> [].
<(L, [R|T]/[R|T]) --> {R =.. [_,Q], \+ member(-Q, L), \+ \+ member(+Q, L), !, member(+Q, L)}.
<(L, [R|T]/[R|T]) --> <(L,T/T).
/<(F, T/T) --> {\+ (member(R,T), R =.. [_,F])}, !.
#(#(P),L,L) :- length(L,P).
