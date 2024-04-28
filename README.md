# TP-EVAL-TDD

## Tests effectués

### CRUD des livres
- Ajouter un livre avec toutes les informations requises. ✅
- Modifier un livre existant. ✅
- Supprimer un livre. ✅
- Rechercher un livre par ISBN. ✅
- Rechercher un livre par titre. ✅
- Rechercher un livre par auteur. ✅

### Gestion des réservations
- Effectuer une réservation pour un adhérent. ✅ // vérifier que l'adhérent existe
- Mettre fin à une réservation. ✅
- Vérifier que la réservation a une date limite correcte. ✅
- Vérifier que l'adhérent ne peut avoir plus de 3 réservations ouvertes simultanées. ✅
- Obtenir la liste des réservations ouvertes. ✅
- Consulter l'historique des réservations pour un adhérent. ✅
- Envoyer un mail de rappel pour les réservations dépassées. ✅

### Intégration avec le web service de référencement
- Vérifier que les informations manquantes sont récupérées correctement depuis le web service ✅

---

## Tests de validation

### ISBN
- Vérifier qu'un ISBN valide est accepté lors de l'ajout d'un livre. ✅
- Vérifier qu'un ISBN invalide est rejeté lors de l'ajout d'un livre. ✅

### Champs requis
- Vérifier qu'un livre ne peut pas être ajouté sans un ISBN valide. ✅
- Vérifier qu'un livre ne peut pas être ajouté sans un titre. ✅
- Vérifier qu'un livre ne peut pas être ajouté sans un auteur. ✅
- Vérifier qu'un livre ne peut pas être ajouté sans un éditeur. ✅
- Vérifier qu'un livre ne peut pas être ajouté sans un format spécifié. ✅

### Format des champs
- Vérifier que le titre d'un livre ne dépasse pas une certaine longueur. ✅
- Vérifier que le nom de l'auteur d'un livre ne dépasse pas une certaine longueur. ✅
- Vérifier que le nom de l'éditeur d'un livre ne dépasse pas une certaine longueur. ✅
- Vérifier que le format d'un livre est parmi les options valides (poche, broché, grand format, etc.). ✅

---

### Adhérent
- Vérifier qu'un adhérent ne peut pas être ajouté sans un code adhérent valide. ✅
- Vérifier qu'un adhérent ne peut pas être ajouté sans un nom. ✅
- Vérifier qu'un adhérent ne peut pas être ajouté sans un prénom. ✅
- Vérifier qu'un adhérent ne peut pas être ajouté sans une date de naissance. ✅
- Vérifier qu'un adhérent ne peut pas être ajouté sans une civilité spécifiée. ✅

### Format des champs
- Vérifier que le nom d'un adhérent ne dépasse pas une certaine longueur. ✅
- Vérifier que le prénom d'un adhérent ne dépasse pas une certaine longueur. ✅
- Vérifier que le code adhérent respecte un format spécifique, s'il y en a un. ✅
- Vérifier que la date de naissance d'un adhérent est dans un format valide et qu'elle est antérieure à la date actuelle. ✅

- Vérifier que le nom d'un adhérent ne dépasse pas une certaine longueur. ✅
- Vérifier que le prénom d'un adhérent ne dépasse pas une certaine longueur. ✅
- Vérifier que le code adhérent respecte un format spécifique, s'il y en a un. ✅
- Vérifier que la date de naissance d'un adhérent est dans un format valide et qu'elle est antérieure à la date actuelle. ✅
