import { BibliotecaComponent } from 'src/app/modules/biblioteca/biblioteca.component';

export default [
    {
        path: '',
        component: BibliotecaComponent,
        children: [{ path: 'livros', loadComponent: () => import('./components/livros/livros.component').then(m => m.LivrosComponent) }],
    },
];
