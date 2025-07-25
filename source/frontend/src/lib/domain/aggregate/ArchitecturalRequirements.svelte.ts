import {Constraint} from "$lib/domain/entity/ar/Constraint.svelte.js";
import {ArchitecturePrinciple} from "$lib/domain/entity/ar/ArchitecturePrinciple.svelte.js";
import {Intention} from "$lib/domain/entity/ar/Intention.svelte.js";
import {NonFunctionalRequirement} from "$lib/domain/entity/ar/NonFunctionalRequirement.svelte.js";

export class ArchitecturalRequirements {
    constraints =  $state<Constraint[]>([]);
    architecturalPrinciples = $state<ArchitecturePrinciple[]>([]);
    intentions = $state<Intention[]>([]);
    nonFunctionalRequirements = $state<NonFunctionalRequirement[]>([]);

    constructor() {}

    static create() : ArchitecturalRequirements {
        return new ArchitecturalRequirements();
    }
}