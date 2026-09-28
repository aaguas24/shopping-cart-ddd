# Git Flow y Pull Request - Guía Paso a Paso

Esta guía te enseña cómo usar **Git Flow** para trabajar con ramas y cómo hacer **Pull Requests** en GitHub para pasar cambios de una rama a otra.

## ¿Qué es Git Flow?

**Git Flow** es un modelo de flujo de trabajo que organiza el desarrollo en ramas especializadas:

- **main**: Código en producción (solo versiones estables)
- **develop**: Rama de integración (versión en desarrollo)
- **feature/**: Nuevas funcionalidades (se crean desde develop)
- **release/**: Preparar versión para producción (se crean desde develop)
- **hotfix/**: Arreglos urgentes en producción (se crean desde main)

---

## Configuración Inicial de Git Flow

### Paso 1: Instalar Git Flow

**En Mac:**
```bash
brew install git-flow
```

**En Windows:**
- Descarga desde: https://git-scm.com/download/win
- Durante la instalación, elige instalar Git Flow

**En Linux (Ubuntu/Debian):**
```bash
sudo apt-get install git-flow
```

### Paso 2: Inicializar Git Flow en tu proyecto

```bash
# Navega a tu proyecto
cd shopping-cart-ddd

# Inicializa git flow
git flow init

# Te hará preguntas, presiona Enter en todas para usar valores por defecto:
# ¿Rama de producción? main
# ¿Rama de desarrollo? develop
# ¿Prefijo para feature? feature/
# ¿Prefijo para release? release/
# ¿Prefijo para hotfix? hotfix/
# ¿Prefijo para support? support/
```

Resultado:
```
✅ Git Flow inicializado correctamente
Estás en la rama: develop
```

---

## Flujo Completo: De Feature a Develop con Pull Request

### Escenario: Desarrollar la feature "Agregar Entidad de Producto"

---

## PASO 1: Crear una nueva Feature

```bash
# Crear y cambiar a la rama feature
git flow feature start agregar-producto-entity

# Esto automáticamente:
# 1. Crea una rama: feature/agregar-producto-entity
# 2. La basa en: develop
# 3. Te cambia a esa rama

# Verificar en qué rama estás:
git branch
# * feature/agregar-producto-entity
#   develop
#   main
```

**¿Qué pasó?**
- Se creó una rama nueva basada en `develop`
- Estás trabajando en una rama aislada
- No afecta a `develop` ni a `main`

---

## PASO 2: Desarrollar la funcionalidad

Crea los archivos y haz cambios en tu rama feature:

```bash
# Crear carpetas si no existen
mkdir -p src/main/java/com/tuempresa/shoppingcart/domain/catalog/entity

# Crear archivo Product.java (ejemplo)
# ... edita el archivo con tu IDE ...

# Ver qué cambios tienes
git status
```

Ejemplo de respuesta:
```
On branch feature/agregar-producto-entity

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        src/main/java/com/tuempresa/shoppingcart/domain/catalog/entity/Product.java

nothing added to commit but untracked files present (tracking what is not staged)
```

---

## PASO 3: Guardar tus cambios (Commits)

```bash
# Agregar todos los cambios al staging
git add .

# Crear un commit con descripción clara
git commit -m "Agregar entidad Product al dominio de catálogo"

# Ver el historial
git log --oneline -3
# a1b2c3d Agregar entidad Product al dominio de catálogo
# d4e5f6g Merge branch 'develop' into 'feature/agregar-producto-entity'
# h7i8j9k Base develop anterior
```

**Reglas para commits:**
- Mensajes claros y descriptivos
- Un cambio por commit
- Verbo en imperativo: "Agregar", "Crear", "Arreglar"

---

## PASO 4: Enviar tu Feature a GitHub

```bash
# Subir tu rama feature a GitHub
git push origin feature/agregar-producto-entity

# Respuesta esperada:
# To github.com:aaguas24/shopping-cart-ddd.git
#  * [new branch]      feature/agregar-producto-entity -> feature/agregar-producto-entity
```

**¿Qué pasó?**
- Tu rama `feature/agregar-producto-entity` está ahora en GitHub
- Otros pueden verla
- Ahora puedes crear un Pull Request

---

## PASO 5: Crear Pull Request en GitHub

### Opción A: Usando el banner que GitHub muestra

1. Ve a tu repositorio: https://github.com/aaguas24/shopping-cart-ddd

2. Verás un banner amarillo:
   ```
   feature/agregar-producto-entity had recent pushes
   Compare & pull request
   ```

3. Haz clic en **"Compare & pull request"**

### Opción B: Manual desde la interfaz

1. Ve a la pestaña **"Pull requests"**

2. Haz clic en **"New pull request"**

3. Selecciona:
   - **Base:** `develop` (destino, a dónde van tus cambios)
   - **Compare:** `feature/agregar-producto-entity` (origen, de dónde vienen)

4. Verifica que los cambios se ven bien

---

## PASO 6: Llenar la información del Pull Request

En la página de crear PR, completa:

```markdown
Title:
Agregar entidad Product al catálogo

Description:
## ¿Qué hace este cambio?
Agrega la entidad Product que representa un producto en el catálogo del sistema.

## ¿Por qué es importante?
Necesitamos la entidad Product para:
- Definir la estructura de un producto
- Crear el repositorio de persistencia
- Exponer endpoints REST

## ¿Qué archivos cambié?
- Creada clase Product.java
- Agregada lógica de equals() y hashCode()

## ¿Cómo probar?
1. Compilar el proyecto: mvn clean install
2. Iniciar la aplicación: mvn spring-boot:run
3. Verificar que compila sin errores

## Checklist
- [x] Código compilado sin errores
- [x] Seguí las convenciones de naming
- [x] Agregué comentarios donde fue necesario
- [x] Probé localmente
```

---

## PASO 7: Esperar Revisión (En Equipo)

**Si trabajas en equipo:**

1. Otros desarrolladores revisarán tu código
2. Pueden:
   - Dejar comentarios
   - Pedir cambios
   - Aprobar el PR

**Si piden cambios:**

```bash
# Vuelve a tu rama feature
git checkout feature/agregar-producto-entity

# Haz los cambios solicitados
# ... edita archivos ...

# Haz commit de los cambios
git add .
git commit -m "Ajustar Product según feedback del review"

# Push (el PR se actualiza automáticamente)
git push origin feature/agregar-producto-entity
```

**Si trabajas solo:**
- Revisa tu propio código
- Asegúrate de que compila
- Procede al siguiente paso

---

## PASO 8: Hacer Merge (Combinar con develop)

Cuando tu PR está aprobado (o listo si trabajas solo):

### Opción A: Usando GitHub (Recomendado)

1. En GitHub, en tu PR, baja hasta encontrar:
   ```
   Merge pull request
   ```

2. Haz clic en **"Merge pull request"**

3. Elige el tipo de merge:
   - **"Create a merge commit"** ← Recomendado para Git Flow
   - "Squash and merge" (simplifica commits)
   - "Rebase and merge" (limpia historial)

4. Haz clic en **"Confirm merge"**

```
✅ Pull request successfully merged and closed
You can now safely delete the branch.
```

### Opción B: Usando Git Flow (Más automático)

```bash
# En tu rama feature
git flow feature finish agregar-producto-entity

# Git Flow automáticamente:
# 1. Mergea tu feature con develop
# 2. Borra la rama feature localmente
# 3. Te coloca en develop

# Push de develop a GitHub
git push origin develop
```

---

## PASO 9: Actualizar tu Local

```bash
# Cambiar a develop
git checkout develop

# Traer los últimos cambios de GitHub
git pull origin develop

# Verificar que tienes los cambios
git log --oneline -3
# a1b2c3d Merge pull request #1 from aaguas24/feature/agregar-producto-entity
# b2c3d4e Agregar entidad Product al dominio de catálogo
# c3d4e5f Merge develop anterior
```

---

## PASO 10: Limpiar Ramas Eliminadas

```bash
# Eliminar la rama feature localmente (si usaste GitHub para merge)
git branch -d feature/agregar-producto-entity

# Eliminar la rama feature en GitHub
git push origin --delete feature/agregar-producto-entity

# Verificar que se eliminó
git branch -a
# * develop
#   main
#   remotes/origin/develop
#   remotes/origin/main
```

---

## Resumen Visual del Flujo

```
1. CREAR FEATURE
   git flow feature start agregar-producto
   ↓
   Rama creada: feature/agregar-producto-entity

2. TRABAJAR EN LA FEATURE
   git add .
   git commit -m "..."
   ↓
   Haces cambios locales

3. ENVIAR A GITHUB
   git push origin feature/agregar-producto-entity
   ↓
   La rama está en GitHub

4. CREAR PULL REQUEST
   GitHub: New pull request
   base: develop ← compare: feature/agregar-producto-entity
   ↓
   PR abierto en GitHub

5. REVISIÓN (SI APLICA)
   Otros revisan el código
   ↓
   Aprobado o piden cambios

6. MERGE
   GitHub: Merge pull request
   ↓
   feature mergeada en develop

7. ACTUALIZAR LOCAL
   git checkout develop
   git pull origin develop
   ↓
   Tienes los cambios localmente

8. LIMPIAR
   git branch -d feature/agregar-producto-entity
   git push origin --delete feature/agregar-producto-entity
   ↓
   Feature eliminada de ambos lados
```

---

## Flujo Completo en Una Línea (Resumen)

```bash
# 1. Crear feature
git flow feature start nombre-feature

# 2. Trabajar (editar archivos)
git add .
git commit -m "Descripción clara"

# 3. Enviar a GitHub
git push origin feature/nombre-feature

# 4. En GitHub: Crear PR de feature/nombre-feature → develop

# 5. En GitHub: Mergear PR

# 6. Actualizar local
git checkout develop
git pull origin develop

# 7. Limpiar
git branch -d feature/nombre-feature
git push origin --delete feature/nombre-feature
```

---

## Comandos Útiles

### Ver en qué rama estás
```bash
git branch
# * develop  (el * indica dónde estás)
#   main
#   feature/agregar-producto-entity
```

### Ver historial de commits
```bash
git log --oneline -5
```

### Ver cambios sin hacer commit
```bash
git status
```

### Ver cambios en un archivo específico
```bash
git diff src/main/java/com/tuempresa/shoppingcart/domain/catalog/entity/Product.java
```

### Deshacer cambios locales (antes de hacer commit)
```bash
git checkout -- archivo.java
```

### Ver diferencias entre ramas
```bash
git diff develop feature/agregar-producto-entity
```

---

## Buenas Prácticas

✅ **Hacer:**
- Un feature = una funcionalidad pequeña
- Commits frecuentes con mensajes claros
- Pull Requests descriptivos
- Revisar código antes de mergear
- Mantener features cortas (no más de 3-4 días de trabajo)

❌ **No hacer:**
- Commits con mensajes vagos ("fix", "cambios")
- Features muy grandes (divídelas)
- Mergear sin revisar
- Dejar ramas abandonadas en GitHub
- Cambiar develop o main directamente

---

## Ejemplo Práctico Completo

```bash
# 1️⃣ Crear feature
git flow feature start customer-entity

# 2️⃣ Crear archivos
mkdir -p src/main/java/com/tuempresa/shoppingcart/domain/customer/entity
# ... crear Customer.java ...

# 3️⃣ Guardar cambios
git add .
git commit -m "Agregar entidad Customer"

# 4️⃣ Enviar a GitHub
git push origin feature/customer-entity

# 5️⃣ En GitHub: Crear PR, llenar detalles, esperar revisión

# 6️⃣ En GitHub: Hacer merge del PR

# 7️⃣ Actualizar local
git checkout develop
git pull origin develop

# 8️⃣ Limpiar
git branch -d feature/customer-entity
git push origin --delete feature/customer-entity

# ✅ Feature completamente integrada en develop
```

---

## Troubleshooting

### Problema: Conflictos en el merge
```bash
# Si hay conflictos, GitHub te lo indicará
# Solución 1: Resolver en GitHub mismo
# Solución 2: Resolver localmente

git checkout feature/nombre-feature
git pull origin develop  # traer cambios de develop
# Arreglar conflictos manualmente
git add .
git commit -m "Resolver conflictos"
git push origin feature/nombre-feature
```

### Problema: Eliminaste una rama por error
```bash
# Recuperar rama eliminada
git reflog
git checkout -b feature/nombre-feature <commit-hash>
```

### Problema: Necesitas cambios recientes de develop
```bash
# Estar en tu feature
git checkout feature/nombre-feature

# Traer cambios de develop
git pull origin develop

# Si hay conflictos, resuelve y haz commit
git add .
git commit -m "Sincronizar con develop"
git push origin feature/nombre-feature
```

---

## Próximos Pasos

Una vez domines el flujo de features, aprenderás:

1. **Release branches**: Para preparar versiones
2. **Hotfix branches**: Para arreglos urgentes en producción
3. **Code review**: Mejorar calidad del código
4. **CI/CD**: Automatizar testing en cada PR

---

## Referencias Rápidas

- [Git Flow Cheatsheet](https://danielkummer.github.io/git-flow-cheatsheet/)
- [GitHub Pull Request Help](https://docs.github.com/en/pull-requests)
- [Conventional Commits](https://www.conventionalcommits.org/)

---

**Última actualización:** 2026-09-28
**Versión:** 1.0
