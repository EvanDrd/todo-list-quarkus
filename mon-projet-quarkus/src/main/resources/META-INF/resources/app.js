// URL de l'API Quarkus pour les todos
const apiUrl = "/todos";

// Charger les todos au démarrage de la page
window.onload = () => {
    fetchTodos();
};

// Fonction pour récupérer tous les todos depuis l'API
function fetchTodos() {
    fetch(apiUrl)
        .then(response => response.json())
        .then(todos => {
            const todoList = document.getElementById('todo-list');
            todoList.innerHTML = ''; // Effacer les anciens todos
            todos.forEach(todo => {
                const li = document.createElement('li');
                li.innerHTML = `
                    <span>${todo.title} - ${todo.description}</span>
                    <input type="checkbox" ${todo.completed ? 'checked' : ''} onchange="toggleTodoStatus(${todo.id}, this.checked)">
                    <button onclick="deleteTodo(${todo.id})">Supprimer</button>
                `;
                todoList.appendChild(li);
            });
        });
}

// Fonction pour ajouter un nouveau todo via l'API
function addTodo() {
    const title = document.getElementById('title').value;
    const description = document.getElementById('description').value;

    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            title: title,
            description: description
        })
    }).then(() => {
        // Recharger la liste des todos après ajout
        fetchTodos();
    });
}

// Fonction pour supprimer un todo
function deleteTodo(id) {
    fetch(`${apiUrl}/${id}`, {
        method: 'DELETE',
    }).then(response => {
        if (response.status === 204) {
            // Recharger la liste des todos après suppression
            fetchTodos();
        } else {
            console.error("Erreur lors de la suppression du Todo");
        }
    }).catch(error => console.error("Error deleting todo:", error));
}


// Fonction pour changer le statut d'un todo (completed ou non)
function toggleTodoStatus(id, completed) {
    fetch(`${apiUrl}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            completed: completed
        })
    }).then(() => {
        // Recharger la liste des todos après modification
        fetchTodos();
    });
}
