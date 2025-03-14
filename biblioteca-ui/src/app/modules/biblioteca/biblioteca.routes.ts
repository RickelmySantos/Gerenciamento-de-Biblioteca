import { BibliotecaComponent } from 'src/app/modules/biblioteca/biblioteca.component';

export default [
    {
        path: '',
        component: BibliotecaComponent,
        children: [{ path: 'home', loadComponent: () => import('./layout/biblioteca-layout.component').then(m => m.BibliotecaLayoutComponent) }],
    },
];
