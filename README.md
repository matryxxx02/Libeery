# Libeery

Application Android projet Master1 E-Services - binôme : Genart Valentin - Fernandes Nicolas

## Fonctionnement Git

### Les branches

Pour développer effectuer un changement, il faut créer une nouvelle branche à partir de *dev*, la nommer selon le format suivant : `<type>/<definition>` où `<type>` est
le contexte du changement, et où `<définition>` est la définition du changement.

*Exemple de types (liste non exhaustive)*
| refactor | feat | dep | fix | release | docs |
| --- | --- | --- | --- | --- | --- |
| Refactorisation de code | Nouvelle fonctionnalité développé | Mise à jour, ajout ou suppression d'une dépendance *(géré par depandabot)* | Correction d'une **issue** | Mise en test d'une nouvelle version de l'API | Ajout de documentation


### Les pull requests

Une fois votre changement effectué, et votre branche créé, il suffit de créer une pull request demandant de *merge* votre branche sur la branche `dev`. 

    dev <- votre/branche
    
Après le code review, vous pouvez **Squash and merge** votre *PR*, en résolvant les éventuels *conflicts*. 
  
### Les issues

Si vous faites face à un problème quelconque lié à Git, au code, ou à une fonctionnalité, il faut créer une *issue* dans la catégorie du même nom. Vous pouvez
ajouter un label correspondant à votre problème (dépendances, fonctionnalité ...).<br/>
Ainsi, vous pourrez lié une *PR* à cette *issue* pour indiquer la résolution du problème.
