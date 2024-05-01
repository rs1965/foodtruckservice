import { CompactTable } from '@table-library/react-table-library/compact';
import { useTheme } from '@table-library/react-table-library/theme';
import { getTheme } from '@table-library/react-table-library/baseline';
const nodes = [
    {
        id: '0',
        name: 'Shopping List',
        deadline: new Date(2020, 1, 15),
        type: 'TASK',
        isComplete: true,
        nodes: 3,
    },
];

const COLUMNS = [
    { label: 'Facility Type', renderCell: (item) => item.name },
    {
        label: 'Address',
        renderCell: (item) =>
            item.deadline.toLocaleDateString('en-US', {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
            }),
    },
    { label: 'Schedule', renderCell: (item) => item.type },
    {
        label: 'Location Description',
        renderCell: (item) => item.isComplete.toString(),
    },
    { label: 'Food Items', renderCell: (item) => item.nodes },
];

const TableCustom = () => {
    const data = { nodes };
    const theme = useTheme(getTheme());
    return <CompactTable columns={COLUMNS} data={data} theme={theme} />;
};

export default TableCustom;